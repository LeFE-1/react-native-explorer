//
//  TableView.m
//  8
//
//  Created by betterton on 2020/1/7.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "TableView.h"

@interface TableView ()<UITableViewDelegate, UITableViewDataSource>

@property (nonatomic, strong) NSArray *dataSource;

@end

@implementation TableView

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
  _tableView = [[UITableView alloc] initWithFrame:CGRectMake(60, 0, size.width, size.height-100) style:UITableViewStylePlain];
  [_tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"cell"];
  _tableView.delegate = self;
  _tableView.dataSource = self;
  [self addSubview:_tableView];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
  UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"cell"];
  cell.selectionStyle = UITableViewCellSelectionStyleNone;
  cell.textLabel.text = _dataSource[indexPath.row];
  return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
  return 90;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
  return CGFLOAT_MIN;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
  return CGFLOAT_MIN;
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
  return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
  return _dataSource.count;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    !self.onClickedItem ? : self.onClickedItem(@{@"index": [NSString stringWithFormat:@"%ld", indexPath.row + 1]});
}

- (void)reloadDataWithDataSource:(NSArray *)dataSource {
  _dataSource = dataSource;
  [_tableView reloadData];
}

- (void)scrollToIndex:(NSInteger)index {
  
  [_tableView scrollToRowAtIndexPath:[NSIndexPath indexPathForRow:index-1 inSection:0] atScrollPosition:UITableViewScrollPositionTop animated:YES];
}

@end
