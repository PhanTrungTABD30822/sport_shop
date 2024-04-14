import torch
import pickle
import warnings
import datetime
import pandas as pd
import seaborn as sns
import torch
import torch.nn as nn
from flask import Flask, jsonify, request
from sklearn.metrics import confusion_matrix
from sklearn.model_selection import StratifiedKFold
from transformers import AutoTokenizer, AutoModel, logging
import os

app = Flask(__name__)
warnings.filterwarnings("ignore")

logging.set_verbosity_error()


cached_bert_model = None

# Function to initialize and cache the BERT model
def get_cached_bert_model():
    global cached_bert_model
    if cached_bert_model is None:
        # Initialize the BERT model
        model_name = "vinai/phobert-base"
        cached_bert_model = AutoModel.from_pretrained(model_name)
    return cached_bert_model

# Usage example


def get_data(path):
    df = pd.read_excel(path, sheet_name=None)['Sheet1']
    df.columns = ['index', 'Emotion', 'Sentence']
    # unused column
    df.drop(columns=['index'], inplace=True)
    return df


train_df = get_data('train_nor_811.xlsx')
valid_df = get_data('valid_nor_811.xlsx')
test_df = get_data('test_nor_811.xlsx')

device = torch.device('cuda:0' if torch.cuda.is_available() else 'cpu')
EPOCHS = 6
N_SPLITS = 5
# We will use Kfold later
train_df = pd.concat([train_df, valid_df], ignore_index=True)
skf = StratifiedKFold(n_splits=N_SPLITS)
for fold, (_, val_) in enumerate(skf.split(X=train_df, y=train_df.Emotion)):
    train_df.loc[val_, "kfold"] = fold

tokenizer = AutoTokenizer.from_pretrained("vinai/phobert-base", use_fast=False)


class SentimentClassifier(nn.Module):
    def __init__(self, n_classes):
        super(SentimentClassifier, self).__init__()
        self.bert = get_cached_bert_model()
        self.drop = nn.Dropout(p=0.3)
        self.fc = nn.Linear(self.bert.config.hidden_size, n_classes)
        nn.init.normal_(self.fc.weight, std=0.02)
        nn.init.normal_(self.fc.bias, 0)

    def forward(self, input_ids, attention_mask):
        last_hidden_state, output = self.bert(
            input_ids=input_ids,
            attention_mask=attention_mask,
            return_dict=False  # Dropout will errors if without this
        )

        x = self.drop(output)
        x = self.fc(x)
        return x


def check_wrong(real_values, predicts):
    wrong_arr = []
    wrong_label = []
    for i in range(len(predicts)):
        if predicts[i] != real_values[i]:
            wrong_arr.append(i)
            wrong_label.append(predicts[i])
    return wrong_arr, wrong_label


cache_real_values = None
cache_predicts = None


def load_data():
    global cache_real_values, cache_predicts

    # Kiểm tra xem dữ liệu đã được cache hay chưa
    if cache_real_values is None or cache_predicts is None:
        # Nếu chưa cache, load dữ liệu từ file
        with open('real_values_and_predicts.pkl', 'rb') as f:
            cache_real_values, cache_predicts = pickle.load(f)

    return cache_real_values, cache_predicts


@app.route('/data', methods=['POST'])
def get_dataz():
    real_values, predicts = load_data()

    print("Ngày và giờ hiện tại:", datetime.datetime.now())
    class_names = ['Enjoyment', 'Disgust', 'Sadness', 'Anger', 'Surprise', 'Fear', 'Other']
    print("Ngày và giờ hiện tại:", datetime.datetime.now())
    sns.heatmap(confusion_matrix(real_values, predicts), annot=False, xticklabels=class_names,
                yticklabels=class_names)
    print("Ngày và giờ hiện tại:", datetime.datetime.now())
    text = request.json.get('text')
    model = SentimentClassifier(n_classes=7).to(device)
    print("Ngày và giờ hiện tại:", datetime.datetime.now())
    encoded_review = tokenizer.encode_plus(
        text,
        max_length=120,
        truncation=True,
        add_special_tokens=True,
        padding='max_length',
        return_attention_mask=True,
        return_token_type_ids=False,
        return_tensors='pt',
    )
    print("Ngày và giờ hiện tại:", datetime.datetime.now())

    input_ids = encoded_review['input_ids'].to(device)
    attention_mask = encoded_review['attention_mask'].to(device)
    print("Ngày và giờ hiện tại:", datetime.datetime.now())
    output = model(input_ids, attention_mask)
    _, y_pred = torch.max(output, dim=1)

    print(f'Text: {text}')
    print(f'Text: {class_names}')
    print(f'Text: {y_pred}')
    print(f'Sentiment: {class_names[y_pred]}')
    return jsonify({'result': class_names[y_pred]})


if __name__ == '__main__':
    app.run(debug=True)
