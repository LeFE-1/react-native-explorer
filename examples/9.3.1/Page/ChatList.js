import React from 'react';
import { View, Text } from 'react-native';
import { Navigation } from "react-native-navigation";


export default class Home extends React.Component {

  static options(passProps) {
    return {
      topBar: {
        title: {
          text: 'Chat List'
        },
      },
    };
  }

  render() {
    return (
      <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
        <Text
          onPress={() => {
            Navigation.mergeOptions(this.props.componentId, {
              sideMenu: {
                left: {
                  visible: true,
                },
              },
            });
          }}
        >click or swiping from left side to open the slide menu</Text>
      </View>
    );
  }
}
