const { NativeModules } = require('react-native');
const { CustomJavaModule } = NativeModules;
const CustomJSModule = {

  echo: function(strFromJava) {
    const msg = 'JS 收到来自 Java 的字符串：' + strFromJava;
    CustomJavaModule.handleJSReturnValue(msg);
  },

};

export default CustomJSModule;