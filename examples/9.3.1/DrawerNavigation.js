import { Navigation } from "react-native-navigation";

import Home from './Page/Home'
import Mine from './Page/Mine'
import Detail from './Page/Detail'
import Setting from './Page/Setting'
import Menu from './Page/Menu'
import ChatList from './Page/ChatList'

Navigation.registerComponent('Home', () => Home);
Navigation.registerComponent('Mine', () => Mine);
Navigation.registerComponent('Detail', () => Detail);
Navigation.registerComponent('Setting', () => Setting);
Navigation.registerComponent('Menu', () => Menu);
Navigation.registerComponent('ChatList', () => ChatList);



Navigation.events().registerAppLaunchedListener(() => {
  Navigation.setRoot({
    root: {
      sideMenu: {
        left: {
          component: {
            name: 'Menu',
          },
        },
        center: {
          stack: {
            children: [{
              component: {
                name: 'ChatList',
              },
            }],
          }
        },
      }
    }
  });
});
