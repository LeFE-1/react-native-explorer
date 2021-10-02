import React, {Component} from 'react';
import {View, Button, Text, ScrollView} from 'react-native';
import RNFS from 'react-native-fs';

export default class App extends Component {

  state = {
    dirFileList: [] // 目录中文件列表
  };

  // RNFS.DocumentDirectoryPath 是 iOS 和 Android 两个平台均有的目录地址
  // 使用 UTF-8 编码
  dir = RNFS.DocumentDirectoryPath;
  path = this.dir + '/test.txt';
  encode = 'utf8';
  subDir = '/subDir';

  componentDidMount() {
    this.checkDirFileList();
  }

  /**
   * 创建文本文件
   */
  createTxtFile = () => {
    // 要写入的内容
    const content = '这是 txt 文件的内容';
    RNFS.writeFile(this.path, content, this.encode)
      .then(success => {
        console.warn('文件已写入！');
        this.checkDirFileList();
      })
      .catch(err => {
        this.warnErr(err);
      });
  };

  /**
   * 读取文本文件
   */
  readTxtFile = () => {
    // 判断文件是否存在
    RNFS.exists(this.path)
      .then(isExists => {
        if (!isExists) {
          console.warn('文件不存在，请先创建！')
          return;
        }
        // 读取文件内容
        RNFS.readFile(this.path, this.encode)
          .then(content => {
            console.warn('读取到了文档内容：', content);
          })
          .catch(err => {
            // 也可以不用先 RNFS.exists() 检查文件是否存在
            // 如果文件不存在，可以在此处接收错误信息并进行逻辑处理
            this.warnErr(err);
          });
      })
      .catch(err => {
        this.warnErr(err);
      });
  };

  /**
   * 读取文本文件
   */
  readTxtFile = () => {
    RNFS.readFile(this.path, this.encode)
      .then(content => {
        console.warn('读取到了文档内容：', content);
      })
      .catch(err => {
        this.warnErr(err);
      });
  };

  /**
   * 删除文本文件
   */
  deleteTxtFile = () => {
    RNFS.unlink(this.path)
      .then(() => {
        console.warn('文件已删除！');
        this.checkDirFileList();
      })
      .catch(err => {
        console.warn('文件不存在，无法完成删除');
      });
  };

  /**
   * 更新文本文件
   */
  updateTxtFile = () => {
    RNFS.appendFile(this.path, '这是更新的内容！')
      .then(() => {
        console.warn('文件更新成功！');
        this.readTxtFile();
      })
      .catch(err => {
        this.warnErr(err);
      });
  };

  /**
   * 上传文本文件
   */
  uploadTxtFile = () => {
    // TODO 替换成你自己的上传地址路径
    const uploadUrl = 'http://requestb.in/XXXXXXX';
    if (uploadUrl.includes('/XXXXXXX')) {
      console.warn('请将上传地址替换为你自己的上传地址路径');
      return;
    }
    // 读取 txt 文件
    RNFS.readDir(this.dir)
      .then(fileList => {
        const txtFile = fileList.filter(file => file.name === 'test.txt')[0];
        const uploadFile = {
          name: txtFile.name.slice(0, txtFile.name.indexOf('.')),
          filename: txtFile.name,
          filepath: txtFile.path,
          filetype: 'text/plain', // 可不传，filetype 会根据 filepath 的扩展名自动生成
        };
        if (!txtFile) {
          console.warn('文件不存在请先创建！');
          return;
        }
        // 上传 txt 文件
        RNFS.uploadFiles({
          toUrl: uploadUrl,
          files: [uploadFile],
          method: 'POST',
          headers: {
            'Accept': 'application/json',
          },
          fields: {
            'hello': 'world',
          },
          begin: () => {
            console.warn('上传开始');
          },
          progress: this.logProgress
        }).then(result => {
          console.warn('上传完毕！');
        }).catch(err => {
          this.warnErr(err);
        });
      }).catch(err => {
      this.warnErr(err);
    });
  };

  /**
   * 下载音乐文件
   */
  downloadMusicFile = () => {
    const downloadUrl = 'http://wvoice.spriteapp.cn/voice/2015/0818/55d2248309b09.mp3';
    const destPath = this.dir + '/song.mp3';
    RNFS.downloadFile({
      fromUrl: downloadUrl,
      toFile: destPath,
      background: true,
      begin: () => {
        console.warn('下载开始')
      },
      progress: this.logProgress
    }).promise.then(result => {
      console.warn('下载完成');
      this.checkDirFileList();
    })
      .catch(err => {
        this.warnErr(err);
      });
  };

  /**
   * 创建目录
   */
  makeDir = () => {
    RNFS.mkdir(this.dir + this.subDir)
      .then(() => {
        console.warn('创建子文件夹 subDir 成功！');
        this.checkDirFileList();
      })
      .catch(err => {
        this.warnErr(err);
      });
  };

  /**
   * 删除文件夹及其内容
   */
  removeDir = () => {
    RNFS.unlink(this.dir + this.subDir)
      .then(() => {
        console.warn('文件夹及其内容已删除！');
        this.checkDirFileList();
      })
      .catch(err => {
        console.warn('文件夹不存在，无法完成删除');
      });
  };

  /**
   * 移动文件至子文件夹
   */
  moveFile = () => {
    const destPath = this.dir + this.subDir + '/test.txt';
    RNFS.moveFile(this.path, destPath)
      .then(() => {
        console.warn('文本文件已移动至子文件夹 subDir 中');
        this.checkDirFileList();
      })
      .catch(err => {
        this.warnErr(err);
      });
  };

  /**
   * 从子文件夹拷贝文本文件
   */
  copyFile = () => {
    const fromPath = this.dir + this.subDir + '/test.txt';
    RNFS.copyFile(fromPath, this.path)
      .then(() => {
        console.warn('文本文件已从子文件夹 subDir 拷贝至' + this.dir + '中');
        this.checkDirFileList();
      })
      .catch(err => {
        this.warnErr(err);
      });
  };

  /**
   * 读取文件夹下的文件列表
   */
  checkDirFileList = () => {
    RNFS.readDir(this.dir)
      .then(fileList => {
        // 将文件列表的文件名组成数组保存下来
        this.setState({dirFileList: fileList.map(file => file.name)});
      })
      .catch(err => {
        this.warnErr(err);
      });
  };

  /**
   * 获取当前文件系统信息
   */
  getFileSystemInfo = () => {
    RNFS.getFSInfo()
      .then(result => {
        console.warn('存储空间：' + result.totalSpace/(1024*1024) + ' MB    空闲空间：' + result.freeSpace/(1024*1024) + ' MB');
      })
      .catch(err => {
        this.warnErr(err);
      });
  };

  /**
   * 获取文件简介
   */
  getFileInfo = () => {
    RNFS.stat(this.path)
      .then(result => {
        const fileInfo =
          '路径：' + result.path + '\n' +
          '创建时间：' + result.ctime + '\n' +
          '修改时间：' + result.mtime + '\n' +
          '占用空间：' + result.size + ' B\n' +
          '读写类型：' + result.mode + '\n' +
          '原始路径：' + result.originalFilepath + '\n' +
          '是否为文件：' + result.isFile() + '\n' +
          '是否为文件夹：' + result.isDirectory();
        console.warn(fileInfo);
      })
      .catch(err => {
        this.warnErr(err);
      });
  };

  /**
   * 设置文件创建修改时间
   */
  setCreateOrModifyTime = () => {
    const createDate = this.buildDate(1994, 7, 25);
    const modifyDate = this.buildDate(2019, 2, 20);
    RNFS.touch(this.path, modifyDate, createDate)
      .then(() => {
        console.warn('日期修改成功！');
        this.getFileInfo();
      })
      .catch(err => {
        this.warnErr(err);
      });
  };

  // 在控制台日志打印上传/下载进度百分比
  logProgress = (response) => {
    const percentage = Math.floor((response.bytesWritten / response.contentLength) * 100);
    console.log('当前进度：' + percentage + '%');
  };

  // 异常内容打印
  warnErr = (error) => {
    console.warn(error.message);
  };

  // 构造日期对象
  buildDate = (year, month, day) => {
    const date = new Date();
    date.setFullYear(year, month - 1, day);
    return date;
  };

  render() {
    const {dirFileList} = this.state;
    return (
      <View style={{flex: 1}}>
        <ScrollView
          style={{flex: 1}}
          contentContainerStyle={{alignItems: 'center', margin: 15}}
        >
          <Text>操作的文件路径为 {this.path} 编码格式为 {this.encode}</Text>
          <View style={{flexDirection: 'row', justifyContent: 'center', width: '100%', marginTop: 20}}>
            <Button title='创建 txt 文件' onPress={this.createTxtFile}/>
            <View style={{width: 20}}/>
            <Button title='删除 txt 文件' onPress={this.deleteTxtFile}/>
          </View>
          <View style={{flexDirection: 'row', justifyContent: 'center', width: '100%', marginTop: 20}}>
            <Button title='更新 txt 文件' onPress={this.updateTxtFile}/>
            <View style={{width: 20}}/>
            <Button title='读取 txt 文件' onPress={this.readTxtFile}/>
          </View>
          <View style={{flexDirection: 'row', justifyContent: 'center', width: '100%', marginTop: 20}}>
            <Button title='上传 txt 文件' onPress={this.uploadTxtFile}/>
            <View style={{width: 20}}/>
            <Button title='下载音乐文件' onPress={this.downloadMusicFile}/>
          </View>
          <View style={{flexDirection: 'row', justifyContent: 'center', width: '100%', marginTop: 20}}>
            <Button title='创建文件夹' onPress={this.makeDir}/>
            <View style={{width: 20}}/>
            <Button title='删除文件夹' onPress={this.removeDir}/>
          </View>
          <View style={{flexDirection: 'row', justifyContent: 'center', width: '100%', marginTop: 20}}>
            <Button title='移动 txt 文件' onPress={this.moveFile}/>
            <View style={{width: 20}}/>
            <Button title='复制 txt 文件' onPress={this.copyFile}/>
          </View>
          <View style={{flexDirection: 'row', justifyContent: 'center', width: '100%', marginTop: 20}}>
            <Button title='获取文件简介' onPress={this.getFileInfo}/>
            <View style={{width: 20}}/>
            <Button title='设置文件创建/修改时间' onPress={this.setCreateOrModifyTime}/>
          </View>
          <View style={{flexDirection: 'row', justifyContent: 'center', width: '100%', marginTop: 20}}>
            <Button title='获取文件系统信息' onPress={this.getFileSystemInfo}/>
          </View>
        </ScrollView>
        <Text style={{marginHorizontal: 15}}>{RNFS.DocumentDirectoryPath} 目录下的文件列表为：</Text>
        <Text style={{margin: 15, backgroundColor: '#d0d0d0', padding: 10}}>{dirFileList.join('\n')}</Text>
      </View>
    );
  }

};