// Jenkinsfile version to be used on Windows or MAC as a slave
pipeline {
    // master executor should be set to 0 in Jenkins
    agent any
    stages {
        stage('Start Grid') {
            steps {
                //sh instead bat for MAC
                bat "docker-compose up -d --scale chrome=2 --scale firefox=2"
            }
        }
        stage('Wait for grid to be ready') {
            steps {
                //sh instead bat for MAC
                bat '''TIMEOUT /T 5'''
            }
        }
        stage('Start tests') {
            steps {
			     bat '''.\\\\gradlew clean test -Dtags="suite:sanity" -DHUB_HOST=hub -Dbrowser=chrome -Denvironment=staging'''
            }
        }
    }
    post{
    		always{
    			archiveArtifacts artifacts: 'target/**'
    			bat "docker-compose down"
    		}
    	}
}