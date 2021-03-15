pipeline {
  agent any
  stages {
    stage('Build App') {
      steps {
        git(branch: 'master', url: 'https://github.com/igorsily/survey.git')
        script {
          def pom = readMavenPom file: 'pom.xml'
          version = pom.version
        }

        sh 'mvn clean install -DskipTests=true'
      }
    }

    stage('Initialize') {
      steps {
        script {
          def pom = readMavenPom file: 'pom.xml'
          version = pom.version
        }

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
  tools {
    maven 'M3'
  }
}