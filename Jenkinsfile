pipeline {
  agent any
  tools {
    maven 'M3'
  }
  stages {
    stage('Build App') {
      steps {
        git(branch: 'master', url: 'https://github.com/igorsily/survey.git')
        sh 'mvn clean install -DskipTests=true'
      }
    }

    stage('Initialize') {
      steps {
        sh '''
                    echo "PATH = ${version}"
                    echo "M3 = ${M3}"
                '''
      }
    }

    stage('Test') {
      steps {
        sh "${mvnCmd} test -Dspring.profiles.active=test"
      }
    }

  }

}