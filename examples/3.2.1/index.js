import React, { Component } from 'react';
import { StyleSheet, View, Text, ScrollView, Image } from 'react-native';

export default class App extends Component {
  render() {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <View>
          <Text style={{ fontSize: 28, color: '#999999' }}>
            我是一段普通文字
            <Text style={{ color: '#333333' }}>我是一段醒目的文字</Text>
          </Text>
        </View>
        <View style={{ marginTop: 10 }}>
          <Text style={{ fontSize: 28, borderColor: '#333333', borderWidth: 1 }}>
            <Image source={require('./icon.png')} style={{ height: 24, width: 24 }} />
            我是一段普通文字
          </Text>
        </View>
        {/*注释的代码在 Android 中会引起崩溃，报错信息如下：*/}
        {/*ReactNative: Exception in native call*/}
        {/*java.lang.RuntimeException: Cannot add a child that doesn't have a YogaNode to a parent without a measure function! (Trying to add a '[RCTTextInlineImage 25]' to a '[RCTView 27]')*/}
        {/*Android 端 Text 嵌套不支持在 View 中再次嵌套组件*/}
        {/*<View style={{ marginTop: 10 }}>*/}
          {/*<Text style={{ fontSize: 28, borderColor: '#333333', borderWidth: 1, lineHeight: 40 }} numberOfLines={2} ellipsizeMode={'tail'}>*/}
            {/*<View style={{ height: 26, width: 26, justifyContent: 'flex-end', alignItems: 'flex-end' }}>*/}
              {/*<Image source={require('./icon.png')} style={{ height: 24, width: 24 }} />*/}
            {/*</View>*/}
            {/*我是一段普通文字我是一段普通文字我是一段普通文字我是一段普通文字我是一段普通文字<Text>我是一段内部文字我是一段内部文字</Text>*/}
          {/*</Text>*/}
        {/*</View>*/}
        {/* 局限性 */}
        <View style={{ marginTop: 10 }}>
          <Text style={{ fontSize: 28 }}>
            我是一段普通文字
            <Text style={{ paddingLeft: 10, borderWidth: 1 }}>左Padding 10</Text>
            <Text style={{ marginLeft: 10, borderWidth: 1 }}>左Margin 10</Text>
          </Text>
        </View>
        <View style={{ marginTop: 10 }}>
          <Text style={{ fontSize: 28, borderColor: '#333333', borderWidth: 1, lineHeight: 40 }}>
            <Image source={require('./icon.png')} style={{ height: 24, width: 24 }} />
            行高失效
          </Text>
          <Text style={{ fontSize: 28, borderColor: '#333333', borderWidth: 1, lineHeight: 40 }}>
            行高<Text>有效</Text>
          </Text>
        </View>
        <View style={{ marginTop: 10 }}>
          <Text style={{ fontSize: 28, borderWidth: 1 }}>
            1. <Text numberOfLines={2} ellipsizeMode={'tail'}>我是一段普通文字我是一段普通文字我是一段普通文字我是一段普通文字我是一段普通文字</Text>
          </Text>
        </View>
      </View>
    );
  }
}
