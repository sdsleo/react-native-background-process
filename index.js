import { NativeModules, AppRegistry } from 'react-native';

const { BackgroundProcess } = NativeModules;

const RNBackgroundProcess = {
    run: ({
        msgTitle = "Background Process", //(default Background Process) enter the notification title  
        msgBody = "Running in the Background", // (default Running in the Background) enter the notification body
        interval = 5000, // (default 0) interval time to restart the execution of the process
        largeIcon = null,
        smallIcon = null, 
        colorIcon = null,
        process = () =>  /* (required) */ console.log("I'm working") /* your code here */
    }) => {
        AppRegistry.registerHeadlessTask(
            'BackgroundProcess', 
            () => async () => process()
        );
        
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
