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
    }
  }

  onPress = () => {
    this.animation = Animated.timing(
      this.state.translateX,
      {
        toValue: 200,
        duration: 10000,
        useNativeDriver: true,
        onComplete: (value) => { console.log('animation end', value) },
      }
    );
    this.animation.start();
  };

  onStop = () => {
    this.animation && this.animation.stop();
  };

  onReset = () => {
    this.animation && this.animation.reset();
  }

  render() {
    console.log('render', this.state.translateX)
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <Animated.View
          style={[
            { width: 50, height: 50, borderWidth: 1 },
            { transform: [{ translateX: this.state.translateX }]}
          ]}
        />
        <View style={{ flexDirection: 'row', marginTop: 40 }}>
          <TouchableWithoutFeedback onPress={this.onPress}>
            <View style={styles.button}>
              <Text>开始</Text>
            </View>
          </TouchableWithoutFeedback>
          <TouchableWithoutFeedback onPress={this.onStop}>
            <View style={styles.button}>
              <Text>结束</Text>
            </View>
          </TouchableWithoutFeedback>
          <TouchableWithoutFeedback onPress={this.onReset}>
            <View style={styles.button}>
              <Text>重置</Text>
            </View>
          </TouchableWithoutFeedback>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  button: {
    width: 50,
    height: 50
  },
});
