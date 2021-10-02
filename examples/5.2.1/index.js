import React, {Component} from 'react';
import {StyleSheet, View, Button, Text, Slider, Switch, PermissionsAndroid, Platform} from 'react-native';
import { Player, Recorder } from '@react-native-community/audio-toolkit';

const MUSIC_URL = 'http://sc1.111ttt.cn/2017/1/11/11/304112002347.mp3'; // 网络音频资源
const MUSIC_FILE_NAME = 'RNAudioTest.mp4'; // 本地音频资源

export default class App extends Component {

  player: Player | null; // 播放器
  recorder: Recorder | null; // 录音器
  lastSeek: number; // 记录用户上次拖动进度条的时间，用于避免 用户拖拽进度条 与 播放时更新进度条 同时更改进度条位置发生冲突
  _progressInterval = null; // 更新进度条的逻辑

  constructor(props) {
    super(props);
    this.state = {
      duration: '-- : --', // 默认的音频持续时长
      progress: -1, // 进度条进度初始值
      mediaStatus: '加载中', // 播放器状态初始值
      useUrl: false, // 控制开关状态，是否切换使用网络音频资源
      audioSource: MUSIC_FILE_NAME, // 音频资源，默认使用本地音频资源
    };
  }

  // 当前进度格式化为 mm : ss 格式的时间
  get progressTime() {
    const {progress} = this.state; // 当前进度条进度
    // 异常情况下默认值
    if (progress == -1 || !this.player || this.player.duration == -1 || !this.player.isPlaying) return '00 : 00';
    const progressTime = this.player.currentTime; // 获取当前播放时长，单位为 ms
    return this.formatDate(progressTime); // 格式化为 mm : ss 格式的时间
  }

  componentDidMount() {
    // 初始化资源
    this.player = null;
    this.recorder = null;
    this.lastSeek = 0;
    this._reloadPlayer();
    this._reloadRecorder();
    // 设定周期任务，每 100 ms 更新一次进度条
    this._progressInterval = setInterval(() => {
      // 在播放状态下和不与用户拖拽进度条逻辑冲突的情况下更新进度条
      if (this.player && this._shouldUpdateProgressBar() && this.player.isPlaying) {
        let currentProgress = Math.max(0, this.player.currentTime) / this.player.duration;
        if (isNaN(currentProgress)) {
          currentProgress = 0;
        }
        this.setState({ progress: currentProgress });
      }
    }, 100);
  }

  componentWillUnmount() {
    clearInterval(this._progressInterval); // 移除周期任务
  }

  // 请求录音权限（Android）
  async _requestRecordAudioPermission() {
    try {
      const granted = await PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.RECORD_AUDIO,
        {
          title: 'Microphone Permission',
          message: 'ExampleApp needs access to your microphone to test react-native-audio-toolkit.',
          buttonNeutral: 'Ask Me Later',
          buttonNegative: 'Cancel',
          buttonPositive: 'OK',
        },
      );
      if (granted === PermissionsAndroid.RESULTS.GRANTED) {
        return true;
      } else {
        return false;
      }
    } catch (err) {
      console.error(err);
      return false;
    }
  }

  // 播放音频
  play = () => {
    this.player.play((err, paused) => {
      if (err) {
        console.warn(err.message);
        return;
      }
      this.setState({mediaStatus: '播放中'});
      console.log(this.player.state);
    });
  };

  // 暂停播放
  pause = () => {
    this.player.pause((err, paused) => {
      if (err) {
        console.warn(err.message);
        return;
      }
      this.setState({mediaStatus: '已暂停'});
      console.log(this.player.state);
    });
  };

  // 停止播放
  stop = () => {
    this.player.stop(() => {
      this.setState({mediaStatus: '已停止'});
      console.log(this.player.state);
    });
  };

  // 开始录音
  startRecord = () => {
    if (this.player) { // 录音与播放不能同时启用（因为本例中操作的是同一个本地文件）
      this.player.destroy();
    }
    // 请求录音权限
    let recordAudioRequest;
    if (Platform.OS == 'android') {
      recordAudioRequest = this._requestRecordAudioPermission();
    } else {
      recordAudioRequest = new Promise(function (resolve, reject) { resolve(true); });
    }

    recordAudioRequest.then((hasPermission) => {
      if (!hasPermission) {
        console.warn('获取录音权限失败');
        return;
      }
      // 请求权限成功，开始录音
      this.recorder.record((err) => {
        if (err) {
          console.warn(err.message);
          return;
        }
        this.setState({mediaStatus: '录音中'});
        console.log(this.player.state);
      });
    });
  };

  // 暂停录音
  pauseRecord = () => {
    this.recorder.pause((err) => {
      if (err) {
        console.warn(err.message);
        return;
      }
      this.setState({mediaStatus: '暂停录音'});
      console.log(this.player.state);
    });
  };

  // 停止录音并保存录音文件
  stopRecord = () => {
    this.recorder.stop((err) => {
      if (err) {
        console.warn(err.message);
      }
      // 重置播放器和录音器
      this._reloadPlayer();
      this._reloadRecorder();
      this.setState({mediaStatus: '已停止'});
      console.log(this.player.state);
    });
  };

  // 切换音频资源
  changeAudioResource = (useUrl) => {
    this.setState({
      useUrl: useUrl,
      audioSource: useUrl ? MUSIC_URL : MUSIC_FILE_NAME // 从网络和本地音频资源中切换
    }, () => {
      // 重置播放器、录音器和进度条
      this._reloadPlayer();
      this._reloadRecorder();
      this.setState({progress: 0});
    });
  };

  // 初始化播放器
  _reloadPlayer() {
    const {audioSource} = this.state; // 播放源
    if (!audioSource) return;
    if (this.player) { // 先销毁旧的实例（如果存在）
      this.player.destroy();
      console.log(this.player.state);
    }
    this.setState({duration: '-- : --'}); // 加载音频的过程中音频总时长信息展示的默认值
    this.player = new Player(audioSource, { // 传入播放源进行实例化
      autoDestroy: false
    }).prepare((err) => { // 加载播放源音频
      if (err) {
        console.log('error at _reloadPlayer():');
        console.log(err);
        return;
      }
      this.setState({duration: this.formatDate(this.player.duration)}); // 加载音频完成后更新音频总时长信息
      console.log(this.player.state);
    });
  }

  // 初始化录音器
  _reloadRecorder() {
    const {useUrl, audioSource} = this.state;
    if (useUrl || !audioSource) return; // 使用网络音频源时，无法录音
    if (this.recorder) { // 销毁旧的录音器实例（如果存在）
      this.recorder.destroy();
      console.log(this.player.state);
    }
    this.recorder = new Recorder(audioSource, { // 传入音频源进行实例化
      bitrate: 256000, // 设置比特率
      channels: 2, // 设置通道
      sampleRate: 44100, // 设置采样率
      quality: 'max' // 设置录音质量
    });
    console.log(this.player.state);
  }

  // 判断是否能够周期更新进度条，避免冲突
  _shouldUpdateProgressBar() {
    return Date.now() - this.lastSeek > 200; // 如果与用户拖拽进度条的时间相隔超过了 200 ms，则可以继续周期更新进度条
  }

  // 用户拖拽进度条更新进度
  _seek(percentage) {
    if (!this.player) {
      return;
    }
    this.lastSeek = Date.now(); // 记录拖拽进度条的时间
    let position = percentage * this.player.duration; // 根据进度换算为要快进/快退至音频的播放位置
    this.player.seek(position, () => { // 快进/快退至播放位置
      console.log('快进/快退成功');
      console.log(this.player.state);
    });
  }

  // 将毫秒时间格式化为 mm : ss 格式的时间
  formatDate(time) {
    const date = new Date(time);
    const mm = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ' : ';
    const ss = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
    return mm + ss;
  }

  render() {
    const {progress, duration, mediaStatus, useUrl} = this.state;
    return (
      <View style={{flex: 2}}>
        <View style={styles.mediaStatusContainer}>
          <Text style={styles.mediaStatusLabel}>{mediaStatus}</Text>
        </View>
        <View style={styles.controllerContainer}>
          <View style={{flexDirection: 'row'}}>
            <Text style={styles.timeLabel}>
              {this.progressTime}
            </Text>
            <Slider style={{flex: 1}} step={0.0001} onValueChange={(percentage) => this._seek(percentage)} value={progress}/>
            <Text style={styles.timeLabel}>
              {duration}
            </Text>
          </View>
          <View style={styles.useUrlContainer}>
            <View>
              <Text style={styles.useUrlLabel}>若播放网络资源，请打开右侧开关</Text>
              <Text style={styles.useUrlBrief}>开关打开时，无法使用录制功能</Text>
            </View>
            <Switch value={useUrl} onValueChange={this.changeAudioResource}/>
          </View>
          <View style={styles.buttonGroup}>
            <Button title={'开始播放'} onPress={this.play}/>
            <Button title={'暂停播放'} onPress={this.pause}/>
            <Button title={'停止播放'} onPress={this.stop}/>
          </View>
          {
            useUrl ?
              null :
              <View style={styles.buttonGroup}>
                <Button title={'开始录制'} onPress={this.startRecord}/>
                <Button title={'暂停录制'} onPress={this.pauseRecord}/>
                <Button title={'停止录制'} onPress={this.stopRecord}/>
              </View>
          }
        </View>
      </View>
    );
  }

};

const styles = StyleSheet.create({
  mediaStatusLabel: {
    fontWeight: 'bold',
    fontSize: 30
  },
  mediaStatusContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center'
  },
  controllerContainer: {
    flex: 1,
    paddingHorizontal: 15
  },
  timeLabel: {
    fontSize: 15
  },
  buttonGroup: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    marginTop: 24
  },
  useUrlContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    height: 50,
    marginTop: 20
  },
  useUrlLabel: {
    fontSize: 15,
    fontWeight: 'bold'
  },
  useUrlBrief: {
    fontSize: 12,
    color: '#999'
  }
});