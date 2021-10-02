import React from 'react';
import {
  Animated,
  View,
  Text,
  TouchableWithoutFeedback,
} from 'react-native';

export default class App extends React.Component {

  constructor(props) {
    super(props)
    const widthValue = new Animated.Value(100);
    widthValue.addListener(this.onWidthChange);
    this.state = {
      width: widthValue,
      position: new Animated.ValueXY({ x: 0, y: 0 }),
    }
  }

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

  onPositionChange = () => {
    Animated.timing(
      this.state.position,
      {
        toValue: { x: 120, y: 120 },
        duration: 1000,
      }
    ).start();
  }

  onWidthChange = (value) => {
    console.log('onWidthChange', value);
  }

  render() {
    const { position } = this.state;
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <Animated.View style={[{ position: 'absolute', borderWidth: 1, width: 50, height: 50 }, { left: position.x, top: position.y }]} />
        <TouchableWithoutFeedback onPress={this.onPositionChange}>
          <View>
            <Text>改变位置</Text>
          </View>
        </TouchableWithoutFeedback>
        <TouchableWithoutFeedback onPress={this.onPress}>
          <Animated.View style={{ width: this.state.width, borderWidth: 1, alignItems: 'center' }}>
            <Text>增加宽度</Text>
          </Animated.View>
        </TouchableWithoutFeedback>
      </View>
    );
  }
}