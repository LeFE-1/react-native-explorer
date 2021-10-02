import React, { Component } from 'react';
import { Image } from 'react-native';

export default class CustomImage extends Component {

  constructor(props) {
    super(props);
    const { prefetchImgs } = props;
    if (prefetchImgs && prefetchImgs instanceof Array) {
      prefetchImgs.forEach(img => {
        Image.prefetch(img)
        // .then(() => {
        //   Image.queryCache(prefetchImgs)
        //     .then(result => {
        //       console.warn('缓存至' + result[img] + '完毕: ' + img);
        //     })
        // }, () => {
        //   console.warn('缓存失败: ' + img);
        // })
      });
    }
  }

  render() {
    const { defaultSource = require('../5.1/icon.png'), ...restProps } = this.props;
    return (
      <Image
        defaultSource={ defaultSource }
        { ...restProps }
      />
    );
  }

}