//
//  LrageListView.m
//  11.3
//
//  Created by betterton on 2020/2/21.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "LrageListView.h"
#import <React/RCTBridge.h>
#import <React/RCTComponent.h>
#import "ListTableViewCell.h"

static NSString *const KCustomTableViewCellID = @"MCCustomTableViewCellID";


@interface LrageListView ()<UITableViewDelegate, UITableViewDataSource>


@property (nonatomic, strong) UITableView *tableView;

// bridge
@property (nonatomic, strong) RCTBridge *bridge;


@property (nonatomic, copy) NSString *itemModuleName;
@property (nonatomic, strong) NSArray *dataSource;
@property (nonatomic, assign) CGFloat itemHeight;


@end


@implementation LrageListView

- (instancetype)initWithBridge:(RCTBridge *)bridge
{
    self = [super initWithFrame:CGRectZero];
    if (self) {
        _bridge = bridge;
        [self createSubViews];
    }
    return self;
}

- (void)createSubViews {
    _tableView = [[UITableView alloc] initWithFrame:self.bounds style:UITableViewStylePlain];
    _tableView.delegate = self;
    _tableView.dataSource = self;
    _tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    [_tableView registerClass:[ListTableViewCell class] forCellReuseIdentifier:KCustomTableViewCellID];

    [self addSubview:_tableView];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    ListTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:KCustomTableViewCellID];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    NSDictionary *content = _dataSource[indexPath.row];
    [cell setupBridge:_bridge withModuleName:_itemModuleName withData:content];
    return cell;
}

#pragma mark -tableViewHeader && Footer
- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return CGFLOAT_MIN;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    return CGFLOAT_MIN;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 3;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return _itemHeight;
}


#pragma mark -refresh
- (void)tableViewReloadDataWith:(NSArray *)dataSource {
    _dataSource = dataSource;
    [_tableView reloadData];
}


- (void)layoutSubviews {
    [super layoutSubviews];
    _tableView.frame = self.bounds;
}

@end
