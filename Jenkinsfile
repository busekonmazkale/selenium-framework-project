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

            publishHTML([
              allowMissing: false,
              alwaysLinkToLastBuild: true,
              keepAll: true,
              reportDir: 'test-output',
              reportFiles: 'ExtentReport.html',
              reportName: 'Test Raporu',
               reportTitles: 'Detaylı Test Sonuçları'
            ])
        }
    }
}