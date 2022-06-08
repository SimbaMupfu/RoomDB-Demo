pipeline{
    agent any

    stages{
        stage('Build'){
            steps{
                git 'https://github.com/SimbaMupfu/RoomDB-Demo.git'
                sh 'echo "Haaa pachiri nebasa"'
                sh 'chmod +x gradlew'
                sh './gradlew clean assembleDebug'
            }
        }
    }
}
