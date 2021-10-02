import React, { Component } from 'react';
import { PanResponder, View } from 'react-native';

export default class App extends Component {
  state = {
    x: 0,
    y: 0
  };

  componentWillMount() {
    this.startX = 0;
    this.startY = 0;
    this._panResponder = PanResponder.create({
      onStartShouldSetPanResponder: (evt, gestureState) => {
        console.log('onStartShouldSetPanResponder', gestureState);
        return true;
      },
      onPanResponderGrant: (evt, gestureState) => {
        console.log('onPanResponderGrant', gestureState)
      },
      onPanResponderStart: (evt, gestureState) => {
        console.log('onPanResponderStart', gestureState)
      },
      onPanResponderMove: (evt, gestureState) => {
        console.log('onPanResponderMove', gestureState)
      },
      onPanResponderEnd: (evt, gestureState) => {
        console.log('onPanResponderEnd', gestureState)
      },
      onPanResponderRelease: (evt, gestureState) => {
        console.log('onPanResponderRelease', gestureState)
      },
    });

    this._dragResponder = PanResponder.create({
      onStartShouldSetPanResponder: (evt, gestureState) => true,
      onPanResponderMove: (evt, gestureState) => {
        this.setState({
          x: this.startX + gestureState.dx,
          y: this.startY + gestureState.dy
        })
      },
      onPanResponderEnd: (evt, gestureState) => {
        this.startX += gestureState.dx;
        this.startY += gestureState.dy;
      }
    });
  }

  render() {
    const { x, y } = this.state;
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <View
          style={{ width: 100, height: 50, borderWidth: 1 }}
          {...this._panResponder.panHandlers}
        />
        {/* 拖拽  */}
        <View
          style={{
            width: 100,
            height: 50,
            borderWidth: 1,
            transform: [
               { translateX: x },
               { translateY: y },
            ]
          }}
          {...this._dragResponder.panHandlers}
        />
      </View>
    )
  }
}
