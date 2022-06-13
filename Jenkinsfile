

pipeline{
    agent { dockerfile true }

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
//                 sh 'yes | sdkmanager --sdk_root=${ANDROID_HOME} --licenses || true'
                sh 'sdkmanager --version'
//                 sh 'sdkmanager "platforms;android-${ANDROID_COMPILE_SDK}"'
//                 sh 'sdkmanager "platform-tools"'
//                 sh 'sdkmanager "build-tools;${ANDROID_BUILD_TOOLS}"'
                sh './gradlew clean assembleDebug'
            }
        }
    }
}
