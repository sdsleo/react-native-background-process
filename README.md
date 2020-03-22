# react-native-background-process

## Getting started

`$ npm install react-native-background-process --save`

ou

`$ yarn add react-native-background-process`

### Mostly automatic installation

`$ react-native link react-native-background-process`

## Usage
```javascript
import BackgroundProcess from 'react-native-background-process';

// Montando processo.
BackgroundProcess.run({
    msgTitle: "Background Process", //("padrao Background Process") insira o titulo da notificação  
    msgBody: "Rodando em Background", // ("padrao Rodando em Background") insira o corpo da notificacao
    largeIcon: null, // ("padrao res/mipmap/ic_launcher") insira um icone grande para subustituir o icone padrao do app 
    smallIcon: null, // ("padrao res/mipmap/ic_notification") insira um icone pequeno para subustituir o icone padrao do app
    colorIcon = null, // ("padrao #black") insira uma cor para o icone pequeno 
    process: () => {} // insira a funcionalidade que ira rodar, sua função terá o tempo de 5 segundos para concluir e reiniciar.
});

// Parando processo
BackgroundProcess.stop();
```
