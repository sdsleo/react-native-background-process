# react-native-background-process
Create a constantly running background task to fetch realtime.

<br>
## Getting started

`$ npm install react-native-background-process --save`

or

`$ yarn add react-native-background-process`

## Usage
```javascript
import BackgroundProcess from 'react-native-background-process';

BackgroundProcess.run({
    msgTitle: "Background Process", //(default Background Process) enter the notification title  
    msgBody: "Running in the Background", // (default Running in the Background) enter the notification body
    interval: 5000, // (default 5000) interval time to restart the execution of the process
    process: function() { // (required)
        console.log("I'm working")
        // your code here
    }
});

// Stop Process
BackgroundProcess.stop();
```
