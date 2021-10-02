import React from 'react';
import {
  Animated,
  View,
  Text,
  TouchableWithoutFeedback,
  Easing,
  TextInput,
  Switch,
} from 'react-native';

export default class App extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      bounciness: 8,
      speed: 12,

      tension: 40,
      friction: 7,

      stiffness: 100,
      damping: 10,
      mass: 1,

      velocity: 0,
      overshootClamping: false,
      restDisplacementThreshold: 0.001,
      restSpeedThreshold: 0.001,

      translateX: new Animated.Value(10),
    }
  }

  onConfigChange = (value, key) => {
    this.setState({ [key]: value }, () => {
      if (['bounciness', 'speed'].indexOf(key) != -1) {
        this.springConfig = {
          bounciness: parseFloat(this.state.bounciness),
          speed: parseFloat(this.state.speed),
        }
      } else if (['tension', 'friction'].indexOf(key) != -1) {
        this.springConfig = {
          tension: parseFloat(this.state.tension),
          friction: parseFloat(this.state.friction),
        }
      } else if (['stiffness', 'damping', 'mass'].indexOf(key) != -1) {
        this.springConfig = {
          stiffness: parseFloat(this.state.stiffness),
          damping: parseFloat(this.state.damping),
          mass: parseFloat(this.state.mass),
        }
      }
    });
  }

  onStart = () => {
    this.setState({ translateX: new Animated.Value(10) }, () => {
      Animated.spring(
        this.state.translateX,
        {
          toValue: 200,
          useNativeDriver: true,
          velocity: parseFloat(this.state.velocity),
          overshootClamping: !!this.state.overshootClamping,
          restDisplacementThreshold: parseFloat(this.state.restDisplacementThreshold),
          restSpeedThreshold: parseFloat(this.state.restSpeedThreshold),
          ...this.springConfig
        }
      ).start();
    })
  };

  render() {
    const { translateX, ...restConfig } = this.state;
    return (
      <View style={{ flex: 1, justifyContent: 'center' }}>
        <Animated.View style={{ transform: [{ translateX }], width: 50, height: 50, backgroundColor: 'black' }} />
        <TouchableWithoutFeedback onPress={this.onStart}>
          <View style={{ alignItems: 'center', paddingVertical: 5 }}>
            <Text>开始动画</Text>
          </View>
        </TouchableWithoutFeedback>
        {
          Object.keys(restConfig).map(attr => (
            <View style={{ flexDirection: 'row', paddingHorizontal: 16, paddingVertical: 5 }}>
              <Text>{ attr }: </Text>
              {
                typeof this.state[attr] === "boolean"
                  ? <Switch
                      value={this.state[attr]}
                      onValueChange={value => this.setState({ [attr]: value })}
                    />
                  : <TextInput
                      style={{ padding: 0, borderBottomWidth: 1, width: 100 }}
                      value={this.state[attr].toString()}
                      onChangeText={value => this.onConfigChange(value, attr)}
                    />
              }
            </View>
          ))
        }
      </View>
    );
  }
}