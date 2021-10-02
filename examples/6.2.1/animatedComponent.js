import React from 'react';
import {
  Animated,
  View,
  Text,
  TouchableWithoutFeedback,
} from 'react-native';

class CustomView extends React.Component {
  render() {
    return (
      <View style={this.props.style}>
        { this.props.children }
      </View>
    )
  }
}

const AnimatedCustomView = Animated.createAnimatedComponent(CustomView);

export default class App extends React.Component {

  state = {
    width: new Animated.Value(100)
  };

  count = 1;

  onPress = () => {
    this.count++;
    Animated.timing(
      this.state.width,
      {
        toValue: 100 + 20 * this.count,
        duration: 1000,
      }
    ).start();
  };

  render() {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <AnimatedCustomView style={{ width: this.state.width, borderWidth: 1, height: 60 }} >
          <Text>自定义动画组件</Text>
        </AnimatedCustomView>
        <TouchableWithoutFeedback onPress={this.onPress}>
          <Text>增加宽度</Text>
        </TouchableWithoutFeedback>
      </View>
    );
  }
}