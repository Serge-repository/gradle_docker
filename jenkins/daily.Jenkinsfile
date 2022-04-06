pipeline {

    agent {
    //add this tag for node in Jenkins
        node("test-executor")
    }

    triggers { cron 'H 08 * * 1-5' } // run at 4 am Kyiv time

    stages {
        stage('Selenium Grid startup') {
            steps {
                sh "docker-compose up -d --scale chrome=1"
            }
        }

        stage('Execute tests'){
            steps {
                sh 'gradle clean test -Dtags="suite:smoke" aggregate'
            }
        }
    }

    post {
        always {
// publish html works only when HTMLPublisher plugin installed
            publishHTML (target: [
                allowMissing         : true,
                alwaysLinkToLastBuild: true,
                keepAll              : true,
                reportDir            : 'target/serenity/',
                reportFiles          : 'index.html',
                reportName           : "Serenity Test Report"
            ])

            sh "docker-compose stop"
            sh "docker-compose rm --force"
        }
    }
}