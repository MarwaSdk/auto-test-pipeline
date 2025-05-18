pipeline {
    agent any
    triggers { 
        githubPush() 
    }
    tools {
        maven "maven3"
        git "Default"
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git(url: 'https://github.com/MarwaSdk/auto-test-pipeline', branch: 'main')
                sh 'mvn -version'
                // Run Maven on a Unix agent.
                sh "mvn clean install"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
    }
}
