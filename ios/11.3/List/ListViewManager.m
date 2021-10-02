//
//  ListViewManager.m
//  11.3
//
//  Created by betterton on 2020/2/21.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "ListViewManager.h"
#import "LrageListView.h"

@implementation ListViewManager

RCT_EXPORT_MODULE(LargeList)

RCT_CUSTOM_VIEW_PROPERTY(data, NSArray, LrageListView) {
    [view tableViewReloadDataWith:json];
}

RCT_EXPORT_VIEW_PROPERTY(itemModuleName, NSString)

RCT_EXPORT_VIEW_PROPERTY(itemHeight, CGFloat)


- (UIView *)view {
    LrageListView *view = [[LrageListView alloc] initWithBridge:self.bridge];
    return view;
}


@end
