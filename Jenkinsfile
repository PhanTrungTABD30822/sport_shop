pipeline {
    agent any

    stages {

        stage("build") {
            steps {
                sh './mvnw clean package'
            }
        }
    }

    post {
        success {
            echo "SUCCESSFUL"
        }
        failure {
            echo "FAILED"
        }
    }
}