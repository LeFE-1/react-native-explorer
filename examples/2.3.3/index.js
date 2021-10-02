import React, { Component } from 'react';
import { StyleSheet, View, Text, PixelRatio } from 'react-native';

export default class App extends Component {
  render() {
    return (
      <View style={{ height: '100%' }}>
        <Text>{ PixelRatio.get() }</Text>
        <View style={{ width: '25%', height: '25%', top: '50%', left: '50%', borderWidth: 1 }}>
          <Text>25%</Text>
        </View>
      </View>
    );
  }
}
