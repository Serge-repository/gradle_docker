pipeline {

    agent {
    //add this tag for node in Jenkins
        node("test-executor")
    }

// build with parameters appear for jenkins pipeline instead of just build after first run
    parameters {
        string(name: 'branch', defaultValue: 'master', description: 'Branch to checkout')
        string(name: 'FILTER', defaultValue: 'tc:12345 or suite:smoke',
            description: '''Serenity filtering execution with tags. Examples:
                            suite:smoke,
                            suite:smoke or tc:12345.
                            But the field can be empty.''')
        string(name: 'test_class', defaultValue: 'gui.LoginPageTests',
             description: '''Test class to be executed. Examples:
                             gui.HomePageTests.
                             But the field can be empty.''')
        choice(name: 'environment', choices: ['staging', 'dev', 'uat'], description: 'Tests run against environment')
        string(name: 'forks', defaultValue: '1', description: 'Number of parallel threads to run')
        choice(name: 'browser', choices: ['chrome', 'firefox'], description: 'Browser to run')
    }

    stages {
        stage('Selenium Grid start') {
            steps {
                sh "docker-compose up -d --scale ${params.browser}=${params.forks}"
            }
        }

        stage('Execute tests'){
            steps {
                script {
                if(!FILTER.isEmpty()) {
                    FILTER = '-Dtags=${FILTER}'
                    }
                }
                sh "gradle clean test ${FILTER} -Denvironment=${params.environment} -Dforks=${params.forks} -Dbrowser=${params.browser} aggregate"
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