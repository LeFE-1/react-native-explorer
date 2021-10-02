import React, { Component } from 'react';
import { StyleSheet, View, Text, ScrollView, TextInput } from 'react-native';

export default class App extends Component {

  state = {
    input: ''
  };

  render() {

    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <TextInput
          style={{ width: 300, height: 40, borderWidth: 1 }}
          value={this.state.input}
          onChangeText={text => this.setState({ input: text })}
        />
        {/* 仿TextArea */}
        <View style={{ height: 10 }} />
        <TextInput
          multiline
          textAlignVertical={'top'}
          style={{ width: 300, height: 40, borderWidth: 1, padding: 0 }}
          value={this.state.input}
          onChangeText={text => this.setState({ input: text })}
        />
        <View style={{ height: 10 }} />
        <TextInput
          multiline
          numberOfLines={2}
          textAlignVertical={'top'}
          style={{ width: 300, borderWidth: 1, padding: 0 }}
          value={this.state.input}
          onChangeText={text => this.setState({ input: text })}
        />
      </View>
    );
  }
}
