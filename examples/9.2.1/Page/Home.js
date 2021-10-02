import React from 'react';
import { View, Text } from 'react-native';


export default class Home extends React.Component {
  render() {
    return (
      <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
        <Text
          onPress={() => { this.props.navigation.navigate({name: 'Detail'})}}
        >Home Screen, click to jump to Detail Screen</Text>
      </View>
    );
  }
}
