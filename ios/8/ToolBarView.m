//
//  ToolBarView.m
//  8
//
//  Created by betterton on 2020/1/6.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "ToolBarView.h"

@interface ToolBarView ()

@property (nonatomic, strong) UIButton *favoButton;
@property (nonatomic, strong) UIButton *sharedButton;

@end

@implementation ToolBarView

- (instancetype)init
{
    self = [super init];
    if (self) {
      [self createSubViews];
    }
    return self;
}

- (void)createSubViews {
  CGSize size = [UIScreen mainScreen].bounds.size;
  
  _sharedButton = [[UIButton alloc] initWithFrame:CGRectMake(0, 0, size.width/3, 66)];
  _sharedButton.backgroundColor = [UIColor lightGrayColor];
  [self addSubview:_sharedButton];
  
  UIButton *buttonComment = [[UIButton alloc] initWithFrame:CGRectMake(size.width/3, 0, size.width/3, 66)];
  [buttonComment setTitle:@"评论" forState:UIControlStateNormal];
  buttonComment.backgroundColor = [UIColor lightGrayColor];
  [self addSubview:buttonComment];
  
  _favoButton = [[UIButton alloc] initWithFrame:CGRectMake((size.width/3) * 2, 0, size.width/3, 66)];
  [_favoButton setTitle:@"未收藏" forState:UIControlStateNormal];
  [_favoButton addTarget:self action:@selector(handleSelectedFavor) forControlEvents:UIControlEventTouchUpInside];
  _favoButton.backgroundColor = [UIColor lightGrayColor];
  [self addSubview:_favoButton];
}

- (void)handleSelectedFavor {
  !self.onClickedFavor ? : self.onClickedFavor(nil);
}

- (void)setFavoriteStatus:(BOOL)isFavo {
    [_favoButton setTitle:isFavo ? @"已收藏" :@"未收藏" forState:UIControlStateNormal];
}

- (void)changeSharedTimes:(NSString *)times {
  [_sharedButton setTitle:[NSString stringWithFormat:@"已分享(%@次)", times] forState:UIControlStateNormal];
}
@end
