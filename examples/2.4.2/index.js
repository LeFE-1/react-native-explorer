import React, { Component } from 'react';
import { StyleSheet, View, Text, ScrollView } from 'react-native';
import ViewOverflow from './ViewOverflow';

export default class App extends Component {
  render() {
    return (
      <ScrollView>
        <View style={{ marginVertical: 50 }}>
          {/*<View style={{ borderWidth: 1, height: 500 }}>*/}
          {/*<View style={{ position: 'absolute', top: 100, left: 100, width: 100, height: 100, borderWidth: 1 }}>*/}
          {/*<Text>item 1</Text>*/}
          {/*</View>*/}
          {/*<View style={{ position: 'absolute', top: '50%', left: '50%', width: 100, height: 100, borderWidth: 1 }}>*/}
          {/*<Text>item 2</Text>*/}
          {/*</View>*/}
          {/*</View>*/}
          <View style={{ borderWidth: 1, height: 500 }}>
            <View style={{ position: 'absolute', top: 0, left: 0, width: 100, height: 100, borderWidth: 1, zIndex: 10, backgroundColor: 'white' }}>
              <Text>only zIndex 10</Text>
            </View>
            <View style={{ position: 'absolute', top: 25, left: 25, width: 100, height: 100, borderWidth: 1, zIndex: 15, backgroundColor: 'white' }}>
              <Text>only zIndex 15</Text>
            </View>
            <View style={{ position: 'absolute', top: 50, left: 50, width: 100, height: 100, borderWidth: 1, backgroundColor: 'white', elevation: 15 }}>
              <Text>only elevation 10 </Text>
            </View>
          </View>

          <ViewOverflow style={{marginVertical: 50}}>
            <View style={{height: 300, width: 300, borderWidth: 1, borderColor: 'gray', flexDirection: 'row'}}>
              <View style={{height: 400, width: 200, borderWidth: 5, borderColor: 'black'}}>
                <Text>子布局</Text>
              </View>
              <Text>父布局</Text>
            </View>
          </ViewOverflow>
        </View>
      </ScrollView>
    );
  }
}
