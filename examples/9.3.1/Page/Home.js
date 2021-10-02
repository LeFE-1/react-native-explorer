import React from 'react';
import { View, Text } from 'react-native';
import { Navigation } from "react-native-navigation";


export default class Home extends React.Component {

  static options(passProps) {
    return {
      topBar: {
        title: {
          text: 'Home'
        },
      },
      bottomTab: {
        text: 'HomeTab',
        icon: require('../ic_tab.png')
      }
    };
  }

  componentDidMount() {
    this.navigationEventListener = Navigation.events().bindComponent(this);
  }

  componentWillUnmount() {
    if (this.navigationEventListener) {
      this.navigationEventListener.remove();
    }
  }

  componentDidAppear() {
    console.log('Detail did appear');
  }

  componentDidDisappear() {
    console.log('Detail did disappear');
  }

  render() {
    return (
      <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
        <Text
          onPress={() => {
            Navigation.push(this.props.componentId, {
              component: {
                name: 'Detail',
                passProps: {
                  text: 'Message from Home'
                },
              }
            });
          }}
        >Home Screen, click to jump to Detail Screen</Text>
      </View>
    );
  }
}
