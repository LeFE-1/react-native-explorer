import React from 'react';
import { View, Text, TouchableOpacity } from 'react-native';
import { Navigation } from "react-native-navigation";


export default class Menu extends React.Component {


  close = () => {
    Navigation.mergeOptions(this.props.componentId, {
      sideMenu: {
        left: {
          visible: false,
        },
      },
    });
  }


  render() {
    return (
      <View style={{ flex: 1, paddingTop: 64, paddingLeft: 12, backgroundColor: '#ffffff' }}>
        <TouchableOpacity
          onPress={this.close}
        >
          <View
            style={{ backgroundColor: '#e5e5e5', marginBottom: 1, height: 44, justifyContent: 'center', paddingHorizontal: 12 }}
          >
            <Text>click to close the slide menu</Text>
          </View>
        </TouchableOpacity>

      </View>
    );
  }
}


