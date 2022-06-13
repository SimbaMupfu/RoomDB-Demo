pipeline{
    agent any

//     export ANDROID_HOME=/Users/Jerry/Library/Android/sdk
//     export PATH=$PATH:$ANDROID_HOME/tools
//     export PATH=$PATH:$ANDROID_HOME/tools/bin
//     export PATH=$PATH/:$ANDROID_HOME/platform-tools

    options {
        // Stop the build early in case of compile or test failures
        skipStagesAfterUnstable()
      }
    stages{
        stage('Genesis'){
            steps{
                sh 'if [ -f "local.properties" ]; then rm "local.properties" fi'
                sh 'touch local.properties'
                sh 'echo "sdk.dir = /Users/4-sure/Library/Android/sdk > local.properties"'
            }
        }
        stage('Compile'){
            steps{
                sh 'chmod +x gradlew'
                sh './gradlew compileDebugSources'
            }
        }
        stage('Build'){
            steps{
                git 'https://github.com/SimbaMupfu/RoomDB-Demo.git'
                sh 'echo "Android SDK path ${ANDROID_SDK_ROOT}"'
                sh 'chmod +x gradlew'
                sh 'yes | sdkmanager --sdk_root=${ANDROID_SDK_ROOT} --licenses || true'
                sh 'sdkmanager --version'
//                 sh 'sdkmanager "platforms;android-${ANDROID_COMPILE_SDK}"'
//                 sh 'sdkmanager "platform-tools"'
//                 sh 'sdkmanager "build-tools;${ANDROID_BUILD_TOOLS}"'
//                 sh './gradlew clean assembleDebug'
            }
        }
    }
}
