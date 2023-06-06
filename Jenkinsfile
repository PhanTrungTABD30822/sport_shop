pipeline {
    agent any
 tools {
        maven 'Maven 3.3.9'
        jdk 'jdk8'
    }
    stage ('Build') {
                steps {
                    sh 'mvn -Dmaven.test.failure.ignore=true install'
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