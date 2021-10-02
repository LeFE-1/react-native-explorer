import React, { Component } from 'react';
import {
  SafeAreaView,
  View,
  requireNativeComponent,
  Dimensions
} from 'react-native'

const screenSize = Dimensions.get('window');
const DEVICE_WIDTH = screenSize.width;
const DEVICE_HEIGHT = screenSize.height;

let count = 0;
const list = Array.from(new Array(1000), () => {
  count++;
  return {text: '这是由JS绘制的内容' + count}
});
const NativeList = requireNativeComponent("LargeList");

export default class App extends Component{
  constructor(props){
    super(props)
  }

  componentDidMount() {
   

  }

  render() {
    return (
      <SafeAreaView style={{flex: 1}}>
        <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center'}}>
        <NativeList
            style={{ height: DEVICE_HEIGHT, width: DEVICE_WIDTH }}
            itemHeight={50}
            itemModuleName={'11_3_items'}
            data={list}
        />
        </View>
      </SafeAreaView>
    );
  }
}