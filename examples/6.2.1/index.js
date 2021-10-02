import React from 'react';
import {
  Animated,
  View,
  Text,
  TouchableWithoutFeedback,
} from 'react-native';

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
    console.log('render')
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <TouchableWithoutFeedback onPress={this.onPress}>
          <Animated.View style={{ width: this.state.width, borderWidth: 1, alignItems: 'center' }}>
            <Text>增加宽度</Text>
          </Animated.View>
        </TouchableWithoutFeedback>
      </View>
    );
  }
}