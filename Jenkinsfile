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

        stages
        {
          stage('Build App')
          {
            steps
             {
            //   git branch: 'master', url: 'https://github.com/igorsily/survey.git'
              script {
                  def pom = readMavenPom file: 'pom.xml'
                  version = pom.version
              }
              sh "mvn clean install -DskipTests=true"
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
          stage('Test')
          {
            steps
            {
              sh "${mvnCmd} test -Dspring.profiles.active=test"
              //step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
            }
          }

        //   stage('Create Image Builder') {

        //     when {
        //       expression {
        //         openshift.withCluster() {
        //           openshift.withProject(env.DEV_PROJECT) {
        //             return !openshift.selector("bc", "bookstore").exists()
        //           }
        //         }
        //       }
        //     }
        //     steps {
        //       script {
        //         openshift.withCluster() {
        //           openshift.withProject(env.DEV_PROJECT) {
        //             openshift.newBuild("--name=bookstore", "--image-stream=redhat-openjdk18-openshift:latest", "--binary=true")
        //           }
        //         }
        //       }
        //     }
        //   }
        //   stage('Build Image') {
        //     steps {
        //       sh "rm -rf ocp && mkdir -p ocp/deployments"
        //       sh "pwd && ls -la target "
        //       sh "cp target/bookstore-*.jar ocp/deployments"

  }

}