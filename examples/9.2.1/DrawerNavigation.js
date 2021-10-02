import * as React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { createDrawerNavigator } from '@react-navigation/drawer';

import Home from './Page/Home'
import Mine from './Page/Mine'
import Detail from './Page/Detail'
import Setting from './Page/Setting'


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


const Drawer = createDrawerNavigator();

function App() {
  return (
    <NavigationContainer>
      <Drawer.Navigator initialRouteName="HomeScreen">
        <Drawer.Screen name="HomeScreen" component={HomeScreen} />
        <Drawer.Screen name="MineScreen" component={MineScreen} />
      </Drawer.Navigator>
    </NavigationContainer>
  );
}

export default App;
