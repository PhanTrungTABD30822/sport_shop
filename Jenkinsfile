pipeline {
    agent any

    stages {

        stage("build") {
            steps {
                sh './mvnw clean package'
            }
        }

        stage('test') {
                    steps {
                        sh './mvnw test'
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