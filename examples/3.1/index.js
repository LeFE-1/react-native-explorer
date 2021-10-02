import React, { Component } from 'react';
import { StyleSheet, View, Text, ScrollView } from 'react-native';

export default class App extends Component {
  render() {
    return (
      <View style={{ flex: 1, justifyContent: 'center' }}>
        <View>
          <Text style={{ fontSize: 40, borderWidth: 1 }}>1</Text>
          <Text style={{ fontSize: 40, borderWidth: 1 }}>2</Text>
          <Text style={{ fontSize: 40, borderWidth: 1 }}>3</Text>
        </View>
        <View style={{ flexDirection: 'row' }}>
          <Text style={{ fontSize: 40, borderWidth: 1 }}>1</Text>
          <Text style={{ fontSize: 40, borderWidth: 1 }}>2</Text>
          <Text style={{ fontSize: 40, borderWidth: 1 }}>3</Text>
        </View>

        <Text style={{ fontSize: 40, borderWidth: 1 }} selectable>长按选择</Text>

        <Text style={{ width: 200, fontSize: 40, borderWidth: 1 }} ellipsizeMode={'head'} numberOfLines={2} >
          文字过长文字过长文字过长文字过长
        </Text>
        <Text style={{ width: 200, fontSize: 40, borderWidth: 1 }} ellipsizeMode={'middle'} numberOfLines={2} >
          文字过长文字过长文字过长文字过长
        </Text>
        <Text style={{ width: 200, fontSize: 40, borderWidth: 1 }} ellipsizeMode={'tail'} numberOfLines={2} >
          文字过长文字过长文字过长文字过长
        </Text>
      </View>
    );
  }
}
