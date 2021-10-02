import React from 'react';
import {
  NativeModules,
  LayoutAnimation,
  Text,
  TouchableOpacity,
  StyleSheet,
  View,
} from 'react-native';

// const { UIManager } = NativeModules;

// UIManager.setLayoutAnimationEnabledExperimental && UIManager.setLayoutAnimationEnabledExperimental(true);

export default class App extends React.Component {
  state = {
    width: 100,
    height: 100,
    top: 0,
    left: 0,
    opacity: 1,
  };

  onPress = () => {
    // LayoutAnimation.linear();
    // this.setState({
    //   width: this.state.width + 15,
    //   height: this.state.height + 15,
    //   top: this.state.top + 10,
    //   left: this.state.left + 10,
    // })
    this.interval = setInterval(() => {
      if (this.state.top == 50) {
        return clearInterval(this.interval)
      }
      this.setState({
        width: this.state.width + 1,
        height: this.state.height + 1,
        top: this.state.top + 1,
        left: this.state.left + 1,
      })
    })
  };

  render() {
    const { width, height, top, left, opacity } = this.state;
    return (
      <View style={styles.container}>
        {/* 仅自身发生变化 */}
        {/*{ opacity ? <View style={[styles.box, { opacity }]} /> : null }*/}
        {/*<View style={[styles.box, { width, height }]} />*/}
        {/* 绝对定位 */}
        <View style={[styles.absoluteBox, { width, height, top, left }]} />
        <TouchableOpacity onPress={this.onPress}>
          <View style={[styles.button]}>
            <Text style={styles.buttonText}>JS动画</Text>
          </View>
        </TouchableOpacity>
        {/*<TouchableOpacity onPress={this.onOpacityPress}>*/}
        {/*  <View style={[styles.button]}>*/}
        {/*    <Text style={styles.buttonText}>透明度动画</Text>*/}
        {/*  </View>*/}
        {/*</TouchableOpacity>*/}
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
