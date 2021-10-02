import React, {Component} from 'react';
import { Image, View, TouchableWithoutFeedback, Text } from 'react-native';
import FastImage from 'react-native-fast-image';

const imgList = [
  {uri: 'https://desk-fd.zol-img.com.cn/t_s4096x2160c5/g2/M00/02/0C/ChMlWV22TtaINGFHAD4NX_LMnYYAANAuAM8HeoAPg13362.jpg'},
  {uri: 'https://desk-fd.zol-img.com.cn/t_s4096x2160c5/g4/M00/00/03/ChMly12xB3KIRCTVACgmyuGk18EAAYPFQPMzm4AKCbi013.jpg'},
  {uri: 'https://desk-fd.zol-img.com.cn/t_s4096x2160c5/g1/M04/0F/0F/ChMljl2f2W-II-2vAEbcng7su_UAAP7oALQx5YARty2717.jpg'}
];

export default class App extends Component{

  constructor(props) {
    super(props);
    this.state = {
      index: 0,
    };
    FastImage.preload(imgList);
  }

  changeImg = () => {
    const { index } = this.state;
    if (index === imgList.length - 1) {
      this.setState({ index: 0 });
      return;
    }
    this.setState({ index: index + 1 });
  };

  render() {
    const { index } = this.state;
    return (
      <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
        <TouchableWithoutFeedback onPress={this.changeImg}>
          <FastImage
            style={{ width: 400, height: 300 }}
            source={imgList[index]}
            resizeMode={ FastImage.resizeMode.contain }
            onLoadStart={() => console.log('onLoadStart')}
            onLoad={(event) => console.log('onLoad') }
            onLoadEnd={() => console.log('onLoadEnd')}
          />
        </TouchableWithoutFeedback>
        <Text>点击大图切换图像</Text>
      </View>
    );
  }
};
