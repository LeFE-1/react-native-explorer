import React from 'react';
import {
  Animated,
  View,
  Text,
  TouchableWithoutFeedback,
  Easing,
  StyleSheet
} from 'react-native';

export default class App extends React.Component {

  constructor(props) {
    super(props)
    this.translateX = new Animated.Value(0);
    // this.translateY = Animated.add(this.translateX, 0);
    this.translateY = Animated.diffClamp(this.translateX, -100, 500);
  }

  onPress = () => {
    Animated.timing(this.translateX, { toValue: 200, duration: 1000 }).start();
  };

  render() {
    const { translateX, translateY } = this;
    return (
      <View style={{ flex: 1, justifyContent: 'center' }}>
        <Animated.View
          style={[
            { width: 50, height: 50, borderWidth: 1 },
            { transform: [{ translateX }, { translateY }]}
          ]}
        />
        <TouchableWithoutFeedback onPress={this.onPress}>
          <View>
            <Text>开始</Text>
          </View>
        </TouchableWithoutFeedback>
      </View>
    );
  }
}
