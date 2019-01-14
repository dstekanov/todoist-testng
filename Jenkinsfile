pipeline {
    agent any
    stages {
        stage('Gradle Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean build'
                sh 'pwd'
            }
        }
        stage('reports') {
            steps {
            script {
                    allure([
                            includeProperties: false,
                            jdk: '',
                            properties: [],
                            reportBuildPolicy: 'ALWAYS',
                            results: [[path: 'todoist-testng/build/allure-results']]
                    ])
            }
            }
        }
    }
}
