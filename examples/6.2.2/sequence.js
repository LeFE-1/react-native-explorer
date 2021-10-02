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
    this.state = {
      translateX: new Animated.Value(0),
      translateY: new Animated.Value(0),
    }
  }

  onPress = () => {
    Animated.sequence([
      Animated.timing(this.state.translateX, { toValue: 200, duration: 1000 }),
      // Animated.delay(500),
      Animated.timing(this.state.translateY, { toValue: 200, duration: 1000 }),
    ]).start();
    // Animated.parallel([
    //   Animated.timing(this.state.translateX, { toValue: 200, duration: 1000 }),
    //   Animated.timing(this.state.translateY, { toValue: 200, duration: 1000 }),
    // ]).start();
  };

  render() {
    const { translateX, translateY } = this.state;
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
