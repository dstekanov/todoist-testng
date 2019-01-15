pipeline {
    agent any

    properties([
        parameters([
            choice(choices: ['chrome', 'firefox'], description: '', name: 'browser')
                  ])
              ])

    stages {
        stage('Gradle Build') {
            steps {
                sh 'chmod +x gradlew'
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
