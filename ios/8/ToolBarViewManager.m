//
//  ToolBarViewManager.m
//  8
//
//  Created by betterton on 2020/1/6.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "ToolBarViewManager.h"
#import "ToolBarView.h"
#import <React/RCTUIManager.h>

@implementation ToolBarViewManager

RCT_EXPORT_MODULE(ToolBarView) // 声明导出模块名称， 如不声明则为类名
RCT_EXPORT_VIEW_PROPERTY(onClickedFavor, RCTBubblingEventBlock)
RCT_CUSTOM_VIEW_PROPERTY(shareTimes, NSString, ToolBarView) {
    [view changeSharedTimes:[RCTConvert NSString:json]];
}

- (UIView *)view {
    return [[ToolBarView alloc] init];
}

RCT_EXPORT_METHOD(favor:(nonnull NSNumber *)reactTag
                  isFavor:(BOOL)isFavor) {
    
    [self.bridge.uiManager addUIBlock:^(RCTUIManager *uiManager, NSDictionary<NSNumber *,UIView *> *viewRegistry) {
        
        ToolBarView *tabBar = (ToolBarView *)viewRegistry[reactTag];
        [tabBar setFavoriteStatus:isFavor];
    }];
}
@end
