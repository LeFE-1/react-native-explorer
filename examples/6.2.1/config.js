import React from 'react';
import {
  Animated,
  View,
  Text,
  TouchableWithoutFeedback,
  Easing,
} from 'react-native';

export default class App extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      scaleX: new Animated.Value(1),
    }
  }

  onPress = () => {
    Animated.timing(
      this.state.scaleX,
      {
        toValue: 2,
        duration: 1000,
        delay: 500,
        easing: Easing.inOut(Easing.ease),
        isInteraction: true,
        useNativeDriver: true,
        onComplete: (value) => { console.log('animation end', value) },
        iterations: 1,
      }
    ).start();
  };

  render() {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <TouchableWithoutFeedback onPress={this.onPress}>
          <Animated.View
            style={[
              { width: 50, borderWidth: 1, alignItems: 'center' },
              { transform: [{ scaleX: this.state.scaleX }]}
            ]}
          >
            <Text>增加宽度</Text>
          </Animated.View>
        </TouchableWithoutFeedback>
      </View>
    );
  }
}