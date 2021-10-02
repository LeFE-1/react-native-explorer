import React, { Component } from 'react';
import {
  SafeAreaView,
  View,
  Text,
  ScrollView
} from 'react-native'

let count = 0;
const list = Array.from(new Array(200), () => count++);

export default class App extends Component{
  constructor(props){
    super(props)
  }

  componentDidMount() {
    const { startTime } = this.props;
    startTime && console.warn('渲染耗时：' + (new Date().getTime() - startTime) + 'ms');
  }

  render() {
    return (
      <SafeAreaView style={{flex: 1}}>
        <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
          <ScrollView style={{flex: 1, width: '100%'}}>
            {
              list.map((item, index) => (
                <View key={index} style={{borderColor: 'black', borderWidth: 1, padding: 4}}>
                  <View style={{borderColor: 'black', borderWidth: 1, padding: 4}}>
                    <View style={{borderColor: 'black', borderWidth: 1, padding: 4}}>
                      <View style={{borderColor: 'black', borderWidth: 1, padding: 4}}>
                        <View style={{borderColor: 'black', borderWidth: 1, padding: 4}}>
                          <View style={{flexDirection: 'row', paddingHorizontal: 15, justifyContent: 'space-between', alignItems: 'center', borderColor: 'black', borderWidth: 1}}>
                            <Text>这是个 Label</Text>
                            <Text>这是个 Value</Text>
                          </View>
                        </View>
                      </View>
                    </View>
                  </View>
                </View>
              ))
            }
          </ScrollView>
        </View>
      </SafeAreaView>
    );
  }
}