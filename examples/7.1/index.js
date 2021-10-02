import React, { Component } from 'react';
import { View, Text, StyleSheet, NativeModules, Button,NativeEventEmitter } from 'react-native'
const { MCRNStorage, MCRNEventEmitter } = NativeModules;
export default class App extends Component {

  constructor(props) {
    super(props);
    this.state={
      count: 1
    }
  }

  toJSON(value){
    console.warn(value)
  }

  exampleMethod = (type) => {
    switch (type) {
      case 100://调用原生常量
        console.warn('原生常量'+ MCRNEventEmitter.COMPONENT_DID_APPEAR);
        break;
      case 101://调用原生方法
        MCRNStorage.clearAllData();
        break;
      case 102://调用原生方法并传递参数
        MCRNStorage.setItem('userName', '张三');
        break;
      case 103://调用原生方法并通过CallBack传值
        MCRNStorage.getItems(['userName'], (result) => {
          console.warn(result); // result
        });
        break;
      case 104://调用原生方法并通过Promise传值
        MCRNStorage.getItem('userName').then(value => this.toJSON(value))
        break;
      case 105:
        const names = [ '张三', '李四', '王五' ];
        const message = {"age":15,"sex":"男"};
        MCRNStorage.saveMsg( names, message, function ( mArray, mMap) {
          window.alert( mArray[0] );
          window.alert( JSON.stringify(mMap) );
        });
        break;
      case 8:
        const currentValue = this.state.count + 1;
        this.setState({count: currentValue});
        break
    }
  };
  componentDidMount() {
    const emitter = new NativeEventEmitter(MCRNEventEmitter);
    emitter.addListener('componentDidAppear', function(data) {
      console.warn(data)
    })
  }

  render() {
    console.warn(this.state.count);
    return (
      <View style={{ flex: 1, alignItems: 'center',justifyContent: 'center' }}>
        <Button title={ '调用原生常量' }  onPress={ () => this.exampleMethod(100) } />
        <View style={{height: 15}}/>
        <Button title={ '调用原生方法' }  onPress={ () => this.exampleMethod(101) } />
        <View style={{height: 15}}/>
        <Button title={ '调用原生方法并传递参数' } onPress={ () => this.exampleMethod(102) } />
        <View style={{height: 15}}/>
        <Button title={ '调用原生方法并通过CallBack传值' } onPress={ () => this.exampleMethod(103) } />
        <View style={{height: 15}}/>
        <Button title={ '调用原生方法并通过Promise传值' } onPress={ () => this.exampleMethod(104) } />
        <View style={{height: 15}}/>
        <Button title={ '调用原生方法并传递数组对象' } onPress={ () => this.exampleMethod(105) } />
        <View style={{height: 15}}/>
        <Text>{this.state.count}</Text>
        <View style={{height: 15}}/>
        <Button title={ '修改state,实现页面渲染'} onPress={ () => this.exampleMethod(8) }/>
      </View>
    );
  }

}
const styles = StyleSheet.create({
content: {
  fontSize: 38,
  color: '#333',
  margin: 15,
  height:100
}
});
