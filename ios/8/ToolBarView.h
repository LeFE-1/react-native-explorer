//
//  ToolBarView.h
//  8
//
//  Created by betterton on 2020/1/6.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTComponent.h>


NS_ASSUME_NONNULL_BEGIN

@interface ToolBarView : UIView

@property (nonatomic, copy) RCTBubblingEventBlock onClickedFavor;

- (void)setFavoriteStatus:(BOOL)isFavo;
- (void)changeSharedTimes:(NSString *)times;


@end

NS_ASSUME_NONNULL_END
