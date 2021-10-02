import React, { Component } from 'react';
import { TouchableHighlight, TouchableOpacity, TouchableNativeFeedback, View, Text, TouchableWithoutFeedback } from 'react-native';

export default class App extends Component {

  render() {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <View
          style={{ width: 100, height: 50, borderWidth: 1 }}
          onStartShouldSetResponder={evt => {
            console.log('onStartShouldSetResponder', evt);
          }}
          onMoveShouldSetResponder={evt => {
            console.log('onMoveShouldSetResponder', evt);
          }}
        />
        <View style={{ height: 50 }} />
        <View
          style={{ width: 100, height: 50, borderWidth: 1 }}
          onStartShouldSetResponder={evt => {
            console.log('onStartShouldSetResponder', evt);
          }}
          onMoveShouldSetResponder={evt => {
            console.log('onMoveShouldSetResponder', evt);
            return true;
          }}
          onResponderGrant={evt => {
            console.log('onResponderGrant')
          }}
          onResponderMove={evt => {
            console.log('onResponderMove')
          }}
          onResponderEnd={evt => {
            console.log('onResponderEnd')
          }}
          onResponderRelease={evt => {
            console.log('onResponderRelease')
          }}
          onTouchStart={e => {
            console.log('View TouchStart');
          }}
          onTouchMove={e => {
            console.log('View TouchMove')
          }}
          onTouchEnd={e => {
            console.log('View TouchEnd')
          }}
        />
        <View style={{ height: 50 }} />
        <View
          style={{ width: 100, height: 50, borderWidth: 1 }}
          onStartShouldSetResponder={evt => {
            console.log('onStartShouldSetResponder')
            return true;
          }}
          onMoveShouldSetResponder={evt => {
            console.log('onMoveShouldSetResponder');
            return false;
          }}
          onResponderGrant={evt => {
            console.log('onResponderGrant')
          }}
          onResponderMove={evt => {
            console.log('onResponderMove')
          }}
          onResponderEnd={evt => {
            console.log('onResponderEnd')
          }}
          onResponderRelease={evt => {
            console.log('onResponderRelease')
          }}
        >
          <View
            style={{ width: 80, height: 40, borderWidth: 1 }}
            onStartShouldSetResponder={evt => {
              console.log('onStartShouldSetResponder', 'child')
              return false;
            }}
            onMoveShouldSetResponder={evt => {
              console.log('onMoveShouldSetResponder', 'child');
              return true;
            }}
            onResponderGrant={evt => {
              console.log('onResponderGrant', 'child')
            }}
            onResponderMove={evt => {
              console.log('onResponderMove', 'child')
            }}
            onResponderEnd={evt => {
              console.log('onResponderEnd', 'child')
            }}
            onResponderRelease={evt => {
              console.log('onResponderRelease', 'child')
            }}
          />
        </View>
        {/* 检测捕获/中断事件 */}
        <View
          style={{ width: 100, height: 50, borderWidth: 1 }}
          onStartShouldSetResponderCapture={evt => {
            console.log('onStartShouldSetResponderCapture')
            return false;
          }}
          onMoveShouldSetResponderCapture={evt => {
            console.log('onMoveShouldSetResponderCapture')
            return true;
          }}
          onStartShouldSetResponder={evt => {
            console.log('onStartShouldSetResponder')
            return false;
          }}
          onMoveShouldSetResponder={evt => {
            console.log('onMoveShouldSetResponder');
            return true;
          }}
          onResponderGrant={evt => {
            console.log('onResponderGrant')
          }}
          onResponderMove={evt => {
            console.log('onResponderMove')
          }}
          onResponderEnd={evt => {
            console.log('onResponderEnd')
          }}
          onResponderRelease={evt => {
            console.log('onResponderRelease')
          }}
          onTouchStart={() => {
            console.log('onTouchStart')
          }}
          onTouchMove={() => {
            console.log('onTouchMove')
          }}
          onTouchEnd={() => {
            console.log('onTouchEnd')
          }}
        >
          <View
            style={{ width: 80, height: 40, borderWidth: 1 }}
            onResponderTerminationRequest={() => {
              console.log('onResponderTerminationRequest', 'child');
              return false;
            }}
            onStartShouldSetResponder={evt => {
              console.log('onStartShouldSetResponder', 'child')
              return false;
            }}
            onMoveShouldSetResponder={evt => {
              console.log('onMoveShouldSetResponder', 'child');
              return true;
            }}
            onResponderGrant={evt => {
              console.log('onResponderGrant', 'child')
            }}
            onResponderMove={evt => {
              console.log('onResponderMove', 'child')
            }}
            onResponderEnd={evt => {
              console.log('onResponderEnd', 'child')
            }}
            onResponderRelease={evt => {
              console.log('onResponderRelease', 'child')
            }}
            onTouchStart={() => {
              console.log('onTouchStart', 'child')
            }}
            onTouchMove={() => {
              console.log('onTouchMove', 'child')
            }}
            onTouchEnd={() => {
              console.log('onTouchEnd', 'child')
            }}
          />
        </View>
      </View>
    )
  }
}
