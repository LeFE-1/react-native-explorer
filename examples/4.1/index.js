import React, { Component } from 'react';
import { StyleSheet, View, Text, Button, Alert } from 'react-native';

export default class App extends Component {

  render() {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <Button
          onPress={() => {
            Alert.alert('Button pressed');
          }}
          title="按钮"
        />
        <View style={{ height: 50 }} />
        <Text
          onPress={() => {
            Alert.alert('Text pressed');
          }}
        >
          Text组件
        </Text>
        <View style={{ height: 50 }} />
        <Text
          style={{ borderWidth: 1, fontSize: 40 }}
          onPress={() => console.log('text pressed')}
          onLongPress={() => console.log('text long pressed')}
          pressRetentionOffset={{ top: 30, left: 30, right: 30, bottom: 30 }}
        >
          Text组件
        </Text>
        <View style={{ height: 50 }} />
        <View
          onTouchStart={e => {
            console.log('View TouchStart', e);
          }}
          onTouchMove={e => {
            console.log('View TouchMove', e.nativeEvent)
          }}
          onTouchEnd={e => {
            console.log('View TouchEnd', e.nativeEvent)
          }}
        >
          <Text style={{ fontSize: 30 }}>View事件</Text>
        </View>
      </View>
    )
  }
}
