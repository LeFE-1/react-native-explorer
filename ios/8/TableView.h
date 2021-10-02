//
//  TableView.h
//  8
//
//  Created by betterton on 2020/1/7.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTComponent.h>

NS_ASSUME_NONNULL_BEGIN

@interface TableView : UIView

@property (nonatomic, copy) RCTBubblingEventBlock onClickedItem;

@property (nonatomic, strong) UITableView *tableView;

- (void)reloadDataWithDataSource:(NSArray *)dataSource;
- (void)scrollToIndex:(NSInteger)index;


@end

NS_ASSUME_NONNULL_END
