pipeline {
    agent any

    tools {
            maven 'Maven'
        }

    stages {
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}