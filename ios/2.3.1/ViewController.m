//
//  ViewController.m
//  2.3.1
//
//  Created by Yanda Zhao on 2019/7/10.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "ViewController.h"
#import <React/RCTBridge.h>
#import <React/RCTBundleURLProvider.h>
#import <React/RCTRootView.h>

@interface ViewController () <RCTBridgeDelegate>

@end

@implementation ViewController

- (void)viewDidLoad {
  [super viewDidLoad];

  // 创建Bridge 和 实例化RCTRootView
  RCTBridge *bridge = [[RCTBridge alloc] initWithDelegate:self launchOptions:nil];
  RCTRootView *rnRootView = [[RCTRootView alloc] initWithBridge:bridge moduleName:@"2_3_1" initialProperties:nil];

  // 设置loadingView的控件, 这里我们用"小菊花"来作为loadingView.
  UIActivityIndicatorView *loadingView = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleGray];
  [loadingView startAnimating];
  rnRootView.loadingView = loadingView;

  // 设置loadingView消失的动画持续时间.(默认为0.25s)
  rnRootView.loadingViewFadeDuration = 0.3;

  // 设置内容加载完成后的loadingView延时展示的时长, 当loadingViewFadeDuration为0时, 此属性不生效.
  rnRootView.loadingViewFadeDelay = 10;

  self.view = rnRootView;
}


- (NSURL *)sourceURLForBridge:(RCTBridge *)bridge
{
#if DEBUG
  return [[RCTBundleURLProvider sharedSettings] jsBundleURLForBundleRoot:@"index" fallbackResource:nil];
#else
  return [[NSBundle mainBundle] URLForResource:@"main" withExtension:@"jsbundle"];
#endif
}


@end
