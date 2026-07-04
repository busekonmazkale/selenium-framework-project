pipeline {
    agent any

    tools {
            maven 'Maven'
        }

    parameters {
            choice(name: 'TEST_GROUP', choices: ['smoke', 'auth'], description: 'Select the test group to execute')
        }

    stages {
        stage('Test') {
            steps {
                sh "mvn test -Dgroups=${params.TEST_GROUP}"
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