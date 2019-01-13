pipeline {
    agent any
    stages {
        stage('Gradle Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean build'
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
                            results: [[path: '/var/jenkins_home/workspace/todoist-testng/build/allure-results']]
                    ])
            }
            }
        }
    }
}
