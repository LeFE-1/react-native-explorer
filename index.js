/**
 * @format
 */

import {AppRegistry} from 'react-native';
import App from './App';
import CustomJSModule from './examples/7.2/CustomJSModule';
const BatchedBridge = require('react-native/Libraries/BatchedBridge/BatchedBridge');

import Example2_2_1 from './examples/2.2.1';
import Example2_3_1 from './examples/2.3.1';
import Example2_3_3 from './examples/2.3.3';
import Example2_4_1 from './examples/2.4.1';
import Example2_4_2 from './examples/2.4.2';
// Text
import Example3_1 from './examples/3.1';
import Example3_1_2 from './examples/3.1.2';
import Example3_2_1 from './examples/3.2.1';
import Example3_2_2 from './examples/3.2.2';
import Example3_3 from './examples/3.3';
import Example3_4_1 from './examples/3.4.1';

// 事件响应
import Example4_1 from './examples/4.1';
import Example4_2 from './examples/4.2';
import Example4_3_1 from './examples/4.3.1';
import Example4_3_2 from './examples/4.3.2';

// 媒体、文件与存储
import Example5_1 from './examples/5.1';
import Example5_1_1 from './examples/5.1.1';
import Example5_1_3 from './examples/5.1.3';
import Example5_2_1 from './examples/5.2.1';
import Example5_2_2 from './examples/5.2.2';
import Example5_3_3 from './examples/5.3.3';
import Example5_5_1 from './examples/5.5.1';

import Example6_1_1 from './examples/6.1.1';
import Example6_1_1_Custom from './examples/6.1.1/custom';
import Example6_2_1 from './examples/6.2.1';
import Example6_2_1_animatedComponent from './examples/6.2.1/animatedComponent';
import Example6_2_1_value from './examples/6.2.1/value';
import Example6_2_1_config from './examples/6.2.1/config';
import Example6_2_1_spring from './examples/6.2.1/spring';
import Example6_2_2 from './examples/6.2.2/';
import Example6_2_2_sequence from './examples/6.2.2/sequence';
import Example6_2_2_operation from './examples/6.2.2/opration';
import Example6_3_2 from './examples/6.3.2';

import Example7_1 from './examples/7.1';
import Example8_1 from './examples/8.1';
import Example8_2 from './examples/8.2';
import Example11_1 from './examples/11.1';
import Example11_2 from './examples/11.2';
import Example11_3 from './examples/11.3';
import Example11_3_items from './examples/11.3/items.js';

// ReactNative导航方案
import ExampleReactNavigation from './examples/9.2.1/App'
// import ExampleReactNaitiveNavigation from './examples/9.3.1/App'

AppRegistry.registerComponent('example', () => App);

AppRegistry.registerComponent('2_2_1', () => Example2_2_1);
AppRegistry.registerComponent('2_3_1', () => Example2_3_1);
AppRegistry.registerComponent('2_3_3', () => Example2_3_3);
AppRegistry.registerComponent('2_4_1', () => Example2_4_1);
AppRegistry.registerComponent('2_4_2', () => Example2_4_2);

// Text
AppRegistry.registerComponent('3_1', () => Example3_1);
AppRegistry.registerComponent('3_1_2', () => Example3_1_2);
AppRegistry.registerComponent('3_2_1', () => Example3_2_1);
AppRegistry.registerComponent('3_2_2', () => Example3_2_2);
AppRegistry.registerComponent('3_3', () => Example3_3);
AppRegistry.registerComponent('3_4_1', () => Example3_4_1);

// 事件
AppRegistry.registerComponent('4_1', () => Example4_1);
AppRegistry.registerComponent('4_2', () => Example4_2);
AppRegistry.registerComponent('4_3_1', () => Example4_3_1);
AppRegistry.registerComponent('4_3_2', () => Example4_3_2);

// 媒体、文件与存储
AppRegistry.registerComponent('5_1', () => Example5_1);
AppRegistry.registerComponent('5_1_1', () => Example5_1_1);
AppRegistry.registerComponent('5_1_3', () => Example5_1_3);
AppRegistry.registerComponent('5_2_1', () => Example5_2_1);
AppRegistry.registerComponent('5_2_2', () => Example5_2_2);
AppRegistry.registerComponent('5_3_3', () => Example5_3_3);
AppRegistry.registerComponent('5_5_1', () => Example5_5_1);

// 动画
AppRegistry.registerComponent('6_1_1', () => Example6_1_1);
AppRegistry.registerComponent('6_1_1_custom', () => Example6_1_1_Custom);
// AppRegistry.registerComponent('6_2_1', () => Example6_2_1);
// AppRegistry.registerComponent('6_2_1', () => Example6_2_1_animatedComponent);
// AppRegistry.registerComponent('6_2_1', () => Example6_2_1_value);
// AppRegistry.registerComponent('6_2_1', () => Example6_2_1_config);
AppRegistry.registerComponent('6_2_1', () => Example6_2_1);
AppRegistry.registerComponent('6_2_2', () => Example6_2_2);
AppRegistry.registerComponent('6_2_2_sequence', () => Example6_2_2_sequence);
AppRegistry.registerComponent('6_2_2_operation', () => Example6_2_2_operation);
AppRegistry.registerComponent('6_3_2', () => Example6_3_2);

//数据交互
AppRegistry.registerComponent('7_1',()=>Example7_1);
AppRegistry.registerComponent('8_1',()=>Example8_1);
AppRegistry.registerComponent('8_2',()=>Example8_2);
BatchedBridge.registerCallableModule(
  'CustomJSModule',
  CustomJSModule,
);

// 性能优化
AppRegistry.registerComponent('11_1',()=>Example11_1);
AppRegistry.registerComponent('11_2',()=>Example11_2);
AppRegistry.registerComponent('11_3',()=>Example11_3);
AppRegistry.registerComponent('11_3_items',()=>Example11_3_items);


// ReactNative导航方案
// React Navigation 示例
AppRegistry.registerComponent('9.2.1',()=> ExampleReactNavigation);

// React Native Navigation 示例, 注意: 如果运行此示例需要注释本文件所有代码, 并把下行代码注释打开
import './examples/9.3.1/index'
