import React, {Component} from 'react';
import {View, Button} from 'react-native';
import AsyncStorage from '@react-native-community/async-storage';

export default class App extends Component {

  // 保存数据
  storeData = async (key, value) => {
    try {
      await AsyncStorage.setItem(key, value);
    } catch (e) {
      console.warn(e);
    }
  };

  // 读取数据
  getData = async (key) => {
    try {
      const value = await AsyncStorage.getItem(key);
      if(value !== null) {
        return value;
      }
    } catch(e) {
      console.warn(e);
    }
    return null;
  };

  // 更新数据
  updateData = async (key, value) => {
    try {
      await AsyncStorage.mergeItem(key, value);
    } catch (e) {
      console.warn(e);
    }
  };

  // 删除数据
  removeData = async (key) => {
    try {
      await AsyncStorage.removeItem(key);
    } catch (e) {
      console.warn(e);
    }
  };

  // 示例：保存用户信息
  storeUser = () => {
    const user = {
      userName: '小明',
      userAge: 18,
      isVip: false
    };
    this.storeData('user', JSON.stringify(user))
      .then(() => {
        console.warn('存储成功！');
      });
  };

  // 示例：读取用户信息
  getUser = () => {
    this.getData('user').then(value => {
      const user = JSON.parse(value);
      const output =
        '用户名称：' + user.userName + '\n' +
        '用户年龄：' + user.userAge + '\n' +
        '是否为会员：' + user.isVip;
      console.warn(output);
    });
  };

  // 示例：更新用户信息
  updateUser = () => {
    const updateUser = {
      userName: '小明',
      userAge: 20,
      isVip: true
    };
    this.updateData('user', JSON.stringify(updateUser))
      .then(() => {
        console.warn('更新成功！');
      });
  };

  // 示例：删除用户信息
  removeUser = () => {
    this.removeData('user')
      .then(() => {
        console.warn('删除成功!')
      })
  };

  render() {
    return (
      <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
        <Button title={'保存用户信息'} onPress={this.storeUser}/>
        <View style={{height: 15}}/>
        <Button title={'读取用户信息'} onPress={this.getUser}/>
        <View style={{height: 15}}/>
        <Button title={'更改用户信息'} onPress={this.updateUser}/>
        <View style={{height: 15}}/>
        <Button title={'删除用户信息'} onPress={this.removeUser}/>
      </View>
    );
  }

}