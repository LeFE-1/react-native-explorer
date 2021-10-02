import React, { Component } from 'react';
import { StyleSheet, View, Text, ScrollView, Image } from 'react-native';

export default class App extends Component {
  render() {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <View>
          <Text style={{ fontSize: 20, borderWidth: 1 }}>
            我是文字<Text style={{ fontSize: 30 }}>我是大一点的文字</Text>
          </Text>
        </View>
        <View style={{ flexDirection: 'row', marginTop: 10, borderWidth: 1 }}>
          <Text style={{ fontSize: 20, textAlignVertical: 'center' }}>我是文字</Text>
          <Text style={{ fontSize: 30 }}>我是大一点的文字</Text>
        </View>
        <View style={{ marginTop: 10 }}>
          <Text style={{ fontSize: 20, borderWidth: 1, textAlignVertical: 'top' }}>
            我是文字<Text style={{ fontSize: 30 }}>我是大一点的文字</Text>
          </Text>
        </View>
        <View style={{ flexDirection: 'row', marginTop: 10, borderWidth: 1, alignItems: 'center' }}>
          <Text style={{ fontSize: 20 }}>我是文字</Text>
          <Text style={{ fontSize: 30 }}>我是大一点的文字</Text>
        </View>
        <View style={{ flexDirection: 'row', marginTop: 10, borderWidth: 1, alignItems: 'flex-start' }}>
          <Text style={{ fontSize: 20 }}>我是文字</Text>
          <Text style={{ fontSize: 30 }}>我是大一点的文字</Text>
        </View>
      </View>
    );
  }
}
