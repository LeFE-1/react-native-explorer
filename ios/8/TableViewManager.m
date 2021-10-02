//
//  TableViewManager.m
//  8
//
//  Created by betterton on 2020/1/7.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "TableViewManager.h"
#import "TableView.h"
#import <React/RCTUIManager.h>

@implementation TableViewManager

RCT_EXPORT_MODULE(ListView) // 声明导出模块名称， 如不声明则为类名
RCT_EXPORT_VIEW_PROPERTY(onClickedItem, RCTBubblingEventBlock)
RCT_CUSTOM_VIEW_PROPERTY(dataSource, NSArray, TableView) {
  [view reloadDataWithDataSource:[RCTConvert NSArray:json]];

}

- (UIView *)view {
    return [[TableView alloc] init];
}

RCT_EXPORT_METHOD(scrollTo:(nonnull NSNumber *)reactTag
                  index:(NSInteger)index) {

    [self.bridge.uiManager addUIBlock:^(RCTUIManager *uiManager, NSDictionary<NSNumber *,UIView *> *viewRegistry) {

        TableView *tableView = (TableView *)viewRegistry[reactTag];
        [tableView scrollToIndex:index];
    }];
}


@end
