import React, { Component } from 'react';
import { 
  View, 
  Text, 
  requireNativeComponent, 
  Dimensions,
  UIManager,
  findNodeHandle } 
  from 'react-native'
const NativeToolBar = requireNativeComponent("ToolBarView");
const DEVICE_WIDTH = Dimensions.get('window').width;
export default class App extends Component{
  constructor(props){
    super(props)
  }

  onFavorClicked = () => {
    console.warn('原生点击了【收藏】按钮');
    setTimeout(() => {
      console.warn('开始改变原生【收藏】状态');
      UIManager.dispatchViewManagerCommand(
        findNodeHandle(this.nativeBar),
        UIManager.getViewManagerConfig('ToolBarView').Commands.favor, // favor为原生方法名称
        [true] //方法入参
      );
    }, 1000);
  }

  render() {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
       <Text>下方是一个由原生画的ToolBar</Text>
       <View style={{ height: 20 }}/>
       <NativeToolBar 
        style={{ height: 66, width: DEVICE_WIDTH }}
        ref={view => this.nativeBar = view}
        shareTimes={10}
        onClickedFavor={this.onFavorClicked}
        />
      </View>
    );
  }
}