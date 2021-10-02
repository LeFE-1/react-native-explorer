import React from 'react';
import { View, Text } from 'react-native';
import { Navigation } from "react-native-navigation";

export default class Mine extends React.Component {

  static options(passProps) {
    return {
      topBar: {
        title: {
          text: 'Mine'
        },
      },
      bottomTab: {
        text: 'MineTab',
        icon: require('../ic_tab.png')
      }
    };
  }

  render() {
    return (
      <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
        <Text
          onPress={() => {
            Navigation.push(this.props.componentId, {
              component: {
                name: 'Setting',
              }
            });
          }}
        >Mine Screen, click to jump to Setting Screen</Text>
      </View>
    );
  }
}
