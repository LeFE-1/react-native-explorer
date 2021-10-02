//
//  ListTableViewCell.h
//  11.3
//
//  Created by betterton on 2020/2/21.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import <UIKit/UIKit.h>
@class RCTBridge;

NS_ASSUME_NONNULL_BEGIN

@interface ListTableViewCell : UITableViewCell

- (void)setupBridge:(RCTBridge *)bridge withModuleName:(NSString *)moduleName withData:(NSDictionary *)data;

@end

NS_ASSUME_NONNULL_END
