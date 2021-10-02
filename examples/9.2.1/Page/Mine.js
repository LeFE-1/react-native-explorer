import React from 'react';
import { View, Text } from 'react-native';


export default class Mine extends React.Component {
  render() {
    return (
      <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
        <Text
          onPress={() => { this.props.navigation.navigate('Setting')}}
        >Mine Screen, click to jump to Setting Screen</Text>
      </View>
    );
  }
}
