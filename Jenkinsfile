// Jenkinsfile version to be used on Windows or MAC as a slave
pipeline {
    // master executor should be set to 0 in Jenkins
//     agent any
agent {
    //add this tag for node in Jenkins
        node("test-executor")
    }
//     triggers {
//             cron('H */8 * * *') //regular builds
//             pollSCM('* * * * *') //polling for changes, here once a minute
//         }
    stages {
        stage('Start Grid') {
            steps {
                //sh instead bat for MAC
                bat "docker-compose up -d --scale chrome=2"
            }
        }
        stage('Start tests') {
            steps {
			     bat '''.\\\\gradlew clean test -Dtags="suite:sanity" -Dbrowser="chrome" -Denvironment="staging"'''
            }
        }
//         stage('Start another tests on another env etc') {
//           steps {
//         	     bat '''.\\\\gradlew test -Dtags="suite:docker" -Dbrowser="firefox" -Denvironment="staging"'''
//           }
//      }
    }
    post{
    	always{
// publish html works only when HTMLPublisher plugin installed
    		publishHTML (target: [
                  allowMissing         : true,
                  alwaysLinkToLastBuild: true,
                  keepAll              : true,
                  reportDir            : 'target/serenity/',
                  reportFiles          : 'index.html',
                  reportName           : "Serenity Test Report"
            ])

//     			archiveArtifacts artifacts: 'target/**'
    			bat "docker-compose stop"
    			bat "docker-compose rm --force"
    		}
    	}
}