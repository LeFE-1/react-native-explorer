import React from 'react';
import {
  NativeModules,
  LayoutAnimation,
  Text,
  TouchableOpacity,
  StyleSheet,
  View,
} from 'react-native';

const { UIManager } = NativeModules;

UIManager.setLayoutAnimationEnabledExperimental && UIManager.setLayoutAnimationEnabledExperimental(true);

export default class App extends React.Component {
  state = {
    width: 100,
    height: 100,
  };

  componentWillUpdate() {
    // LayoutAnimation.configureNext(
    //   LayoutAnimation.create(2000, LayoutAnimation.Types.easeInEaseOut, LayoutAnimation.Properties.opacity)
    // , () => {
    //   console.log('Animation End')
    // });
    LayoutAnimation
      .configureNext({
        duration: 2000,
        create: {
          type: LayoutAnimation.Types.linear,
          delay: 1000,
          property: LayoutAnimation.Properties.scaleX,
        },
        update: {
          initialVelocity: 1,
          springDamping: 0.5,
          type: LayoutAnimation.Types.spring,
          property: LayoutAnimation.Properties.opacity,
        },
        delete: {
          type: LayoutAnimation.Types.easeIn,
          property: LayoutAnimation.Properties.opacity,
        }
      })
  }

  onPress = () => {
    this.setState({
      width: this.state.width + 100,
      height: this.state.height + 100,
    })
  };

  render() {
    const { width, height, top, left, opacity } = this.state;
    return (
      <View style={styles.container}>
        { width > 100 && width < 350 ? <View style={[styles.box, { width, height }]} /> : null }
        <TouchableOpacity onPress={this.onPress}>
          <View style={[styles.button]}>
            <Text style={styles.buttonText}>动画</Text>
          </View>
        </TouchableOpacity>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 200,
    height: 200,
    backgroundColor: 'red',
  },
  button: {
    backgroundColor: 'black',
    paddingHorizontal: 20,
    paddingVertical: 15,
    marginTop: 15,
  },
  buttonText: {
    color: '#fff',
    fontWeight: 'bold',
  },
  absoluteBox: {
    position: 'absolute',
    width: 100,
    height: 100,
    backgroundColor: 'black',
    top: 0,
    left: 0,
  }
});