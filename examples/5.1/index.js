import React, {Component} from 'react';
import {
  View,
  Image,
  ImageBackground,
  Text
} from 'react-native';

export default class App extends Component {

  render() {

    const shadowsStyling = {
      width: 100,
      height: 100,
      shadowColor: "#000000",
      shadowOpacity: 0.8,
      shadowRadius: 2,
      shadowOffset: {
        height: 1,
        width: 0
      }
    };

    return (
      <View style={{ justifyContent: 'space-around', alignItems: 'center', flex: 1 }}>
        <Image
          style={{marginVertical: 10}}
          source={require('./icon.png')}
        />
        <Image
          style={{width: 50, height: 50, marginVertical: 10}}
          source={{uri: 'https://video512.oss-cn-beijing.aliyuncs.com/IMG_6959.jpeg'}}
        />
        <Image
          style={{width: 50, height: 50, marginVertical: 10}}
          source={{uri: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADMAAAAzCAYAAAA6oTAqAAAAEXRFWHRTb2Z0d2FyZQBwbmdjcnVzaEB1SfMAAABQSURBVGje7dSxCQBACARB+2/ab8BEeQNhFi6WSYzYLYudDQYGBgYGBgYGBgYGBgYGBgZmcvDqYGBgmhivGQYGBgYGBgYGBgYGBgYGBgbmQw+P/eMrC5UTVAAAAABJRU5ErkJggg=='}}
        />
        <View styles={shadowsStyling}>
          <Image
            style={{width: 50, height: 50, marginVertical: 10, borderRadius: 10}}
            source={{uri: 'https://video512.oss-cn-beijing.aliyuncs.com/IMG_6959.jpeg'}}
          />
        </View>
        <ImageBackground
          style={{ width: 50, height: 50 }}
          source={{ uri: 'https://video512.oss-cn-beijing.aliyuncs.com/IMG_6959.jpeg'}}
        >
          <View style={{ width: 30, height: 30, backgroundColor: '#FFFFFF' }} />
        </ImageBackground>

        <ImageBackground
          style={{ width: 50, height: 50 }}
          source={{ uri: 'https://video512.oss-cn-beijing.aliyuncs.com/IMG_6959.jpeg'}}
        >
          <Text style={{ color: '#ffffff' }}>背景为图片的文字</Text>
        </ImageBackground>

      </View>
    );
  }

};
