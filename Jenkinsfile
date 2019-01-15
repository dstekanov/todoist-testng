pipeline {
    agent any

    stages {
        stage('Gradle Build') {
            steps {
                sh 'chmod +x gradlew'
                sh 'echo ${browser}'
                sh 'echo $browser'
                sh './gradlew clean test -Dbrowser=${browser}'
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
                            results: [[path: 'build/allure-results']]
                    ])
            }
            }
        }
    }
}
