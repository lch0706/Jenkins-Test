pipeline {
    agent any

    tools {
        jdk 'JDK-17'
        gradle 'Gradle-7'
        nodejs 'NodeJS'
    }

    environment {
        JAVA_HOME = "/usr/lib/jvm/java-17-openjdk-amd64"
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Clone Repository') {
            steps {
                git url: 'https://github.com/lch0706/Jenkins-Test.git', branch: 'main', credentialsId: 'GIT-token1'
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend') {
                    sh 'chmod +x gradlew'
                    sh '''
                        export GRADLE_OPTS="-Dorg.gradle.java.home=/usr/lib/jvm/java-17-openjdk-amd64"
                        ./gradlew clean build --info
                    '''
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

        stage('Test Backend') {
            steps {
                dir('backend') {
                    sh './gradlew test'
                }
            }
        }

        stage('Deploy') {
            steps {
                sh './deploy.sh'
            }
        }
    }
}
