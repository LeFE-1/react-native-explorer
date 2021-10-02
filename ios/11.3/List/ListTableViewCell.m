//
//  ListTableViewCell.m
//  11.3
//
//  Created by betterton on 2020/2/21.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "ListTableViewCell.h"
#import <React/RCTRootView.h>

@interface ListTableViewCell ()

@property (nonatomic, strong) RCTRootView *rootView;
@property (nonatomic, copy) NSString *moduleName;

@end


@implementation ListTableViewCell


- (void)setupBridge:(RCTBridge *)bridge withModuleName:(NSString *)moduleName withData:(NSDictionary *)data {
    BOOL isLegal = [data isKindOfClass:[NSDictionary class]] && [moduleName isKindOfClass:[NSString class]];
    if (!isLegal) return;
    [_rootView removeFromSuperview];
    _rootView = nil;
    _moduleName = moduleName;
    
    _rootView = [[RCTRootView alloc] initWithBridge:bridge moduleName:moduleName initialProperties:data];
    [self.contentView addSubview:_rootView];

}

- (void)layoutSubviews {
    [super layoutSubviews];
    _rootView.frame = self.contentView.frame;
}

- (UIView *)hitTest:(CGPoint)point withEvent:(UIEvent *)event {
    UIView *hitView = [super hitTest:point withEvent:event];
    if(hitView == self){
        return nil;
    }
    return hitView;
}

@end
