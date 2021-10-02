import { Navigation } from "react-native-navigation/lib/dist/index";

import Home from './Page/Home'
import Mine from './Page/Mine'
import Detail from './Page/Detail'
import Setting from './Page/Setting'
import Menu from './Page/Menu'

Navigation.registerComponent('Home', () => Home);
Navigation.registerComponent('Mine', () => Mine);
Navigation.registerComponent('Detail', () => Detail);
Navigation.registerComponent('Setting', () => Setting);
Navigation.registerComponent('Menu', () => Menu);


Navigation.events().registerAppLaunchedListener(() => {
  Navigation.setRoot({
    root: {
      bottomTabs: {
        children: [
          {
            component: {
              name: 'Home',
            }
          },
          {
            component: {
              name: 'Mine',
            }
          }]
      }
    }
  });
});

const DidAppearListener = Navigation.events().registerComponentDidAppearListener(
  ({componentId, componentName, passProps}) => {
    console.log(componentName, componentId, passProps, 'DidAppear')
  }
);

const DidDisappearListener = Navigation.events().registerComponentDidDisappearListener(
  ({componentId, componentName}) => {
    console.log(componentId, componentName, 'DidDisappear')
  }
);

