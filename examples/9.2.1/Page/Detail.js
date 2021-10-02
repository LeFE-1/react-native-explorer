import React from 'react';
import { View, Text } from 'react-native';


export default class Detail extends React.Component {
  render() {
    return (
      <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
        <Text
          onPress={() => { this.props.navigation.push('Detail')}}
        >Detail Screen</Text>
      </View>
    );
  }
}
