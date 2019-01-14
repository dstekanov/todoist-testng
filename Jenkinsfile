pipeline {
    agent any
    stages {
        stage('Gradle Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean build'
                sh '${WORKSPACE} 2>&1'
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
                            results: [[path: '${WORKSPACE}/build/allure-results']]
                    ])
            }
            }
        }
    }
}
