pipeline {
    agent any

     stages {
         stage ('Build') {
                 steps {
                     sh 'mvn -Dmaven.test.failure.ignore=true install'
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