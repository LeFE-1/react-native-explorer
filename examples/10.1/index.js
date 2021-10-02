import React, { Component } from 'react';
import { View, Text, StyleSheet, NativeModules, Button,NativeEventEmitter } from 'react-native'
const { MCRNStorage } = NativeModules;
export default class App extends Component {
  constructor(props){
    super(props)
  }

  checkHotFix = () => {

  };

  render() {
      return (
      <View>
        <Button title={ '检查热更新' } style={ styles.content } onPress={ this.checkHotFix() } />
      </View>
      );
       }
}