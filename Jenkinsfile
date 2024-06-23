pipeline {
    agent any

    environment {
        JAVA_HOME = "/usr/lib/jvm/java-17-openjdk-amd64" // 수동으로 설치된 JDK 경로
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
                    //sh './mvnw clean install'
                    sh 'chmod +x gradlew'
                    sh '''
                    export GRADLE_OPTS="-Dorg.gradle.java.home=/usr/lib/jvm/java-17-openjdk-amd64"
                    sh './gradlew  clean build'
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
                    //sh './mvnw test'
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
