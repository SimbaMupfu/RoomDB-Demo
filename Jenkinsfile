pipeline{
    agent any

//     export ANDROID_HOME=/Users/Jerry/Library/Android/sdk
//     export PATH=$PATH:$ANDROID_HOME/tools
//     export PATH=$PATH:$ANDROID_HOME/tools/bin
//     export PATH=$PATH/:$ANDROID_HOME/platform-tools

    stages{
        stage('Build'){
            steps{
                git 'https://github.com/SimbaMupfu/RoomDB-Demo.git'
                sh 'echo "Android SDK path ${ANDROID_HOME}"'
                sh 'chmod +x gradlew'
//                 sh './gradlew clean assembleDebug'
            }
        }
    }
}
