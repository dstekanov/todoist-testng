pipeline {
    agent any

    stages {
        stage('Checkstyle') {
            steps {
                sh './gradlew check'
            }
        }

        stage('Test') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean test -Dbrowser=${browser}'
            }
        }

        stage('Report') {
            steps {
            script {
                    allure([
                            includeProperties: false,
                            jdk: '',
                            properties: [],
                            reportBuildPolicy: 'ALWAYS',
                            results: [[path: 'build/allure-results']]
                    ])
            }
            }
        }
    }
}
