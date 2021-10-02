//
//  ViewController.m
//  2.2.1
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
  self.view.backgroundColor = [UIColor lightGrayColor];

  // 创建Bridge 和 实例化RCTRootView
  RCTBridge *bridge = [[RCTBridge alloc] initWithDelegate:self launchOptions:nil];
  RCTRootView *rnRootView = [[RCTRootView alloc] initWithBridge:bridge moduleName:@"2_2_1" initialProperties:nil];

  // 设置RCTRootView的Frame
  CGFloat screenWidth = [UIScreen mainScreen].bounds.size.width;
  rnRootView.frame = CGRectMake(0, 100, screenWidth, 140);
  
  // 添加RCTRootView
  [self.view addSubview:rnRootView];
  
  UILabel *label = [[UILabel alloc] init];
  label.text = @"这里是原生视图";
  [label sizeToFit];
  [self.view addSubview:label];
  label.center = CGPointMake(screenWidth / 2.0f, 280);
  
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
