import React, { Component } from 'react';
import {
  SafeAreaView,
  View, 
  Text, 
  TouchableWithoutFeedback,
  requireNativeComponent, 
  Dimensions,
  UIManager,
  findNodeHandle } 
  from 'react-native'
const NativeList = requireNativeComponent("ListView");
const DEVICE_WIDTH = Dimensions.get('window').width;
const DEVICE_HEIGHT = Dimensions.get('window').height;
let index = 0;
const data = Array.from(new Array(1000), () => {
  return '第 ' + index++ + ' 行';
});

export default class App extends Component{
  constructor(props){
    super(props)
  }

  onClicked = (nativeData) => {
    const data = nativeData.nativeEvent ? nativeData.nativeEvent : nativeData;
    console.warn('原生点击了第'+data['index']+'行');
  };

  onPressScrollToButton = () => {
    console.warn('滚动原生list到第10行');
    UIManager.dispatchViewManagerCommand(
      findNodeHandle(this.nativeList),
      UIManager.getViewManagerConfig('ListView').Commands.scrollTo, // favor为原生方法名称
      [10] //方法入参
    );
  };

  render() {
    return (
      <SafeAreaView style={{flex: 1}}>
        <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
          <Text style={{height: 20}}>下方是一个原生列表</Text>
          <NativeList
            style={{ height: DEVICE_HEIGHT-100, width: DEVICE_WIDTH }}
            ref={view => this.nativeList = view}
            dataSource={data}
            onClickedItem={this.onClicked}
          />
          <TouchableWithoutFeedback onPress={this.onPressScrollToButton}>
            <View style={{ height: 30, backgroundColor: 'gray', justifyContent: 'center', alignItems: 'center' }}>
              <Text style={{ color: 'white' }}>点击滚动视图</Text>
            </View>
          </TouchableWithoutFeedback>
        </View>
      </SafeAreaView>
    );
  }
}