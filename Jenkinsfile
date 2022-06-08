pipeline{
    agent any

    stages{
        stage('Build'){
            steps{
                git 'https://github.com/SimbaMupfu/RoomDB-Demo.git'
                sh './gradlew clean assembleDebug'
            }
        }
    }
}
