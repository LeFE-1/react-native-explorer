import React, { Component } from 'react';
import { PanResponder, View } from 'react-native';

export default class App extends Component {

  componentWillMount() {
    this.startX = 0;
    this.startY = 0;

    this._dragResponder = PanResponder.create({
      onStartShouldSetPanResponder: (evt, gestureState) => true,
      onPanResponderMove: (evt, gestureState) => {
        this._component.setNativeProps({
          style: {
            transform: [
              { translateX: this.startX + gestureState.dx },
              { translateY: this.startY + gestureState.dy },
            ]
          }
        })
      },
      onPanResponderEnd: (evt, gestureState) => {
        this._component.setNativeProps({
          style: {
            transform: [
              { translateX: this.startX + gestureState.dx },
              { translateY: this.startY + gestureState.dy },
            ]
          }
        })
      }
    });
  }

  render() {
    console.log('render');
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        {/* 拖拽  */}
        <View
          ref={component => this._component = component}
          style={{
            width: 100,
            height: 50,
            borderWidth: 1,
          }}
          {...this._dragResponder.panHandlers}
        />
      </View>
    )
  }
}
