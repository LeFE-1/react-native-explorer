import * as React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';

import Home from './Page/Home'
import Mine from './Page/Mine'
import Detail from './Page/Detail'
import Setting from './Page/Setting'

const Tab = createBottomTabNavigator();

const HomeStack = createStackNavigator();
const MineStack = createStackNavigator();

const HomeScreen = () => (
  <HomeStack.Navigator>
    <HomeStack.Screen name="Home" component={Home} />
    <HomeStack.Screen name="Detail" component={Detail} />
  </HomeStack.Navigator>
)

const MineScreen = () => (
  <MineStack.Navigator>
    <MineStack.Screen name="Mine" component={Mine} />
    <MineStack.Screen name="Setting" component={Setting} />
  </MineStack.Navigator>
)

function App() {
  return (
    <NavigationContainer>
      <Tab.Navigator>
        <Tab.Screen name="HomeTab" component={HomeScreen} />
        <Tab.Screen name="MineTab" component={MineScreen} />
      </Tab.Navigator>
    </NavigationContainer>
  );
}

export default App;
