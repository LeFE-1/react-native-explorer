import React, { Component } from 'react';
import { TouchableHighlight, TouchableOpacity, TouchableNativeFeedback, View, Text, TouchableWithoutFeedback } from 'react-native';

export default class App extends Component {

  render() {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <TouchableWithoutFeedback onPress={() => {}}>
          <View style={{ borderWidth: 1 }}>
            <Text style={{ width: 300, fontSize: 34 }}>TouchableWithoutFeedback</Text>
          </View>
        </TouchableWithoutFeedback>
        <View style={{ height: 50 }} />
        <TouchableHighlight onPress={() => {}}>
          <View style={{ borderWidth: 1 }}>
            <Text style={{ width: 300, fontSize: 34 }}>TouchableHighlight</Text>
          </View>
        </TouchableHighlight>
        <View style={{ height: 50 }} />
        <TouchableOpacity onPress={() => {}}>
          <View style={{ borderWidth: 1 }}>
            <Text style={{ width: 300, fontSize: 34 }}>TouchableOpacity</Text>
          </View>
        </TouchableOpacity>
        <View style={{ height: 50 }} />
        {/*TouchableNativeFeedback 在 Android 平台可以为按钮添加一个点击扩散的动画效果，在 iOS 平台则会引发崩溃，故此处注释*/}
        {/*<TouchableNativeFeedback onPress={() => {}}>*/}
          {/*<View style={{ borderWidth: 1 }}>*/}
            {/*<Text style={{ width: 300, fontSize: 34 }}>TouchableNativeFeedback</Text>*/}
          {/*</View>*/}
        {/*</TouchableNativeFeedback>*/}

        <TouchableWithoutFeedback
          onPressIn={() => {
            this.start = new Date().getTime();
            console.log('onPressIn', this.start)
          }}
          onPressOut={() => {
            console.log('onPressOut', new Date().getTime() - this.start)
          }}
          onPress={() => {
            console.log('onPress', new Date().getTime() - this.start)
          }}
          onLongPress={() => {
            console.log('onLongPress', new Date().getTime() - this.start)
          }}
        >
          <View style={{ borderWidth: 1 }}>
            <Text style={{ width: 300, fontSize: 34 }}>Press In/Out/Long</Text>
          </View>
        </TouchableWithoutFeedback>

        <TouchableWithoutFeedback
          delayPressIn={1000}
          delayPressOut={1000}
          onPressIn={() => {
            this.start = new Date().getTime();
            console.log('onPressIn', this.start)
          }}
          onPressOut={() => {
            console.log('onPressOut', new Date().getTime() - this.start)
          }}
          onPress={() => {
            console.log('onPress', new Date().getTime() - this.start)
          }}
        >
          <View style={{ borderWidth: 1 }}>
            <Text style={{ width: 300, fontSize: 34 }}>delayPressIn/Out</Text>
          </View>
        </TouchableWithoutFeedback>

        <TouchableOpacity
          hitSlop={{ top: 50, bottom: 50, left: 50, right: 50 }}
        >
          <View style={{ width: 50, height: 50, borderWidth: 1 }}>
            <Text>hitSlop</Text>
          </View>
        </TouchableOpacity>
        <TouchableOpacity
          pressRetentionOffset={{ top: 50, bottom: 50, left: 50, right: 50 }}
        >
          <View style={{ width: 50, height: 50, borderWidth: 1 }}>
            <Text>pressRetentionOffset</Text>
          </View>
        </TouchableOpacity>

        {/* 嵌套 */}
        <TouchableWithoutFeedback onPress={() => {
          console.log('Press outer')
        }}>
          <View style={{ borderWidth: 1 }}>
            <TouchableWithoutFeedback onPress={() => {
              console.log('Press inner')
            }}>
              <View style={{ borderWidth: 1 }}>
                <Text style={{ fontSize: 34 }}>Press</Text>
              </View>
            </TouchableWithoutFeedback>
          </View>
        </TouchableWithoutFeedback>
      </View>
    )
  }
}
