import React from 'react';
import { View, Text } from 'react-native';
import { Navigation } from "react-native-navigation";


export default class Detail extends React.Component {

  static options(passProps) {
    return {
      topBar: {
        title: {
          text: 'Detail'
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
        >Detail Screen, click to return to the previous page</Text>
        <Text>{this.props.text}</Text>
      </View>
    );
  }
}
