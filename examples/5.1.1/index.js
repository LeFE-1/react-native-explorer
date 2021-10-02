import React, {Component} from 'react';
import {
  View,
  Text,
  Image,
  TouchableWithoutFeedback
} from 'react-native';
import CustomImage from "./custom";

const imgList = [
  'https://desk-fd.zol-img.com.cn/t_s4096x2160c5/g2/M00/02/0C/ChMlWV22TtaINGFHAD4NX_LMnYYAANAuAM8HeoAPg13362.jpg',
  'https://desk-fd.zol-img.com.cn/t_s4096x2160c5/g4/M00/00/03/ChMly12xB3KIRCTVACgmyuGk18EAAYPFQPMzm4AKCbi013.jpg',
  'https://desk-fd.zol-img.com.cn/t_s4096x2160c5/g1/M04/0F/0F/ChMljl2f2W-II-2vAEbcng7su_UAAP7oALQx5YARty2717.jpg'
];

const foodUrl = 'https://video512.oss-cn-beijing.aliyuncs.com/IMG_6959.jpeg';

export default class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      index: 0,
    };
    Image.queryCache(imgList)
      .then(result => {
        imgList.forEach(url => {
          console.warn('页面刚打开时图片缓存在' + result[url] + '中: ' + url);
        })
      })
  }

  changeImg = () => {
    const { index } = this.state;
    if (index === imgList.length - 1) {
      this.setState({ index: 0 }, () => {
        Image.queryCache(imgList)
          .then(result => {
            console.warn('当前图片缓存在' + result[imgList[index]] + '中');
          })
      });
      return;
    }
    this.setState({ index: index + 1 }, () => {
      Image.queryCache(imgList)
        .then(result => {
          console.warn('当前图片缓存在' + result[imgList[index]] + '中');
        })
    });
  };

  render() {
    const { index } = this.state;
    return (
      <View style={{ justifyContent: 'center', alignItems: 'center', flex: 1 }}>
        <Text style={{textAlign: 'center'}}>以下 Image 组件均设置为 width: 50, height: 100，resizeMode 设置为不同的值的效果如下：</Text>
        <View style={{ flexWrap: 'wrap', flexDirection: 'row' }}>
          <View style={{alignItems: 'center', margin: 5}}>
            <Text>cover</Text>
            <Image
              style={{
                height: 100,
                width: 50,
                backgroundColor: 'blue'
              }}
              resizeMode={'cover'}
              source={{ uri: foodUrl }}
            />
          </View>

          <View style={{alignItems: 'center', margin: 5}}>
            <Text>contain</Text>
            <Image
              style={{
                height: 100,
                width: 50,
                backgroundColor: 'blue'
              }}
              resizeMode={'contain'}
              source={{ uri: foodUrl }}
            />
          </View>

          <View style={{alignItems: 'center', margin: 5}}>
            <Text>stretch</Text>
            <Image
              style={{
                height: 100,
                width: 50,
                backgroundColor: 'blue'
              }}
              resizeMode={'stretch'}
              source={{ uri: foodUrl }}
            />
          </View>

          <View style={{alignItems: 'center', margin: 5}}>
            <Text>repeat</Text>
            <Image
              style={{
                height: 100,
                width: 50,
                backgroundColor: 'blue'
              }}
              resizeMode={'repeat'}
              source={{ uri: foodUrl }}
            />
          </View>

          <View style={{alignItems: 'center', margin: 5}}>
            <Text>center</Text>
            <Image
              style={{
                height: 100,
                width: 50,
                backgroundColor: 'blue'
              }}
              resizeMode={'center'}
              source={{ uri: foodUrl }}
            />
          </View>

        </View>
        <TouchableWithoutFeedback onPress={this.changeImg}>
          <CustomImage
            prefetchImgs={ imgList }
            source={{ uri: imgList[index] }}
            style={{ height: 300, width: 400 }}
          />
        </TouchableWithoutFeedback>
        <Text>点击大图切换图像</Text>
      </View>
    );
  }

};
