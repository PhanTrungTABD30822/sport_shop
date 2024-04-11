package com.shop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestEmoji {
    private String text;
    private Emoji result;

    public RequestEmoji(String text) {
        this.text = text;
    }
}
