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
        task
    }) => {
        
        const fn = async () => {
            task();
        } 

        AppRegistry.registerHeadlessTask('BackgroundProcess', () => fn);
        BackgroundProcess.startService(msgTitle, msgBody, smallIcon, largeIcon, colorIcon, interval);
    }
}

export default RNBackgroundProcess;
