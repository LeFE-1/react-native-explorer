import React, { Component } from 'react';
import { StyleSheet, View, Text, Keyboard, TextInput } from 'react-native';

export default class App extends Component {

  state = {
    input: ''
  };

  componentDidMount () {
    this.keyboardWillShowListener = Keyboard.addListener('keyboardWillShow', this.keyboardWillShow);
    this.keyboardDidShowListener = Keyboard.addListener('keyboardDidShow', this.keyboardDidShow);
    this.keyboardDidHideListener = Keyboard.addListener('keyboardWillHide', this.keyboardWillHide);
    this.keyboardDidHideListener = Keyboard.addListener('keyboardDidHide', this.keyboardDidHide);
    this.keyboardWillChangeFrameListener = Keyboard.addListener('keyboardWillChangeFrame', this.keyboardWillChangeFrame);
    this.keyboardDidChangeFrameListener = Keyboard.addListener('keyboardDidChangeFrame', this.keyboardDidChangeFrame);
  }

  componentWillUnmount() {
    this.keyboardDidShowListener.remove();
    this.keyboardWillShowListener.remove();
    this.keyboardDidHideListener.remove();
    this.keyboardWillChangeFrameListener.remove();
    this.keyboardDidChangeFrameListener.remove();
  }

  keyboardWillShow(e) {
    console.log('keyboardWillShow', e);
  }

  keyboardDidShow(e) {
    console.log('keyboardDidShow', e);
  }

  keyboardDidHide(e) {
    console.log('keyboardDidHide', e);
  }

  keyboardWillHide(e) {
    console.log('keyboardWillHide', e)
  }

  keyboardWillChangeFrame(e) {
    console.log('keyboardWillChangeFrame', e)
  }

  keyboardDidChangeFrame(e) {
    console.log('keyboardDidChangeFrame', e)
  }

  render() {

    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <TextInput
          style={{ width: 300, height: 40, borderWidth: 1 }}
          value={this.state.input}
          onChangeText={text => this.setState({ input: text })}
        />
      </View>
    );
  }
}
