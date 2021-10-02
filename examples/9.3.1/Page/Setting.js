import React from 'react';
import { View, Text } from 'react-native';
import { Navigation } from "react-native-navigation";

export default class Setting extends React.Component {

  static options(passProps) {
    return {
      topBar: {
        title: {
          text: 'Setting'
        },
      }
    };
  }

  render() {
    return (
      <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
        <Text
          onPress={() => {
            Navigation.pop(this.props.componentId);
          }}
        >Setting Screen, click to return to the previous page</Text>
      </View>
    );
  }
}
