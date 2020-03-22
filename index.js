import { NativeModules, AppRegistry } from 'react-native';

const { BackgroundProcess } = NativeModules;

const RNBackgroundProcess = {
    run: ({
        msgTitle = "Background Process", 
        msgBody = "Rodando em Background", 
        largeIcon = null,
        smallIcon = null, 
        colorIcon = null,
        interval = 5000,
        process
    }) => {
        AppRegistry.registerHeadlessTask('BackgroundProcess', () => async () => {
            process();
        });
        
        BackgroundProcess.startService(
            msgTitle, 
            msgBody, 
            smallIcon, 
            largeIcon, 
            colorIcon, 
            interval
        );
    },
    stop: () => {
        BackgroundProcess.stopService();
    }
}

export default RNBackgroundProcess;
