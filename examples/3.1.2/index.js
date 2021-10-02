import React, { Component } from 'react';
import { StyleSheet, View, Text, ScrollView } from 'react-native';

export default class App extends Component {
  render() {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <View style={{ flexDirection: 'row' }}>
          <Text style={{ backgroundColor: 'black', color: 'white', fontSize: 28, lineHeight: 40 }}>我是一段文字agl</Text>
          <View style={{ backgroundColor: '#666666', height: 42 }}><Text style={{ color: 'white' }}>42</Text></View>
          <View style={{ backgroundColor: '#999999', height: 40 }}><Text style={{ color: 'white' }}>40</Text></View>
          {/*<View style={{ width: 10, height: 40, backgroundColor: 'red' }} />*/}
          {/*<Text style={{ backgroundColor: 'black', color: 'white', fontSize: 28, lineHeight: 26 }}>我是一段文字</Text>*/}
        </View>
        <View style={{ flexDirection: 'row', marginTop: 10 }}>
          <View>
            <Text style={{ backgroundColor: 'black', color: 'white', fontSize: 28 }}>我是一段文字</Text>
          </View>
          <View style={{ backgroundColor: '#999999', height: 30 }}><Text style={{ color: 'white' }}>30</Text></View>
        </View>
        <View style={{ flexDirection: 'row', marginTop: 10 }}>
          <View>
            <Text style={{ backgroundColor: 'black', color: 'white', fontSize: 28 }}>我是一段文字agl</Text>
          </View>
          <View style={{ backgroundColor: '#999999', height: 34 }}><Text style={{ color: 'white' }}>34</Text></View>
        </View>
        <View style={{ flexDirection: 'row', width: 200, marginTop: 10 }}>
          <Text style={{ flex: 1, backgroundColor: 'black', color: 'white', fontSize: 28 }}>我是一段文字</Text>
          <View style={{ flex: 1, height: 56, backgroundColor: 'red' }} />
        </View>
        <View style={{ marginTop: 10 }}>
          <Text style={{ fontSize: 28, color: '#999999' }}>
            我是一段普通文字
            <Text style={{ color: '#333333' }}>我是一段醒目的文字</Text>
          </Text>
        </View>

      </View>
    );
  }
}
