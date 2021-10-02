//
//  MCRNEventEmitter.h
//  MCRNBridge
//
//  Created by Yanda Zhao on 2019/10/14.
//  Copyright © 2019 rnexplorer. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>

static NSString *_Nonnull kMCRNEventInternalCommunicationKey = @"kMCRNEventInternalCommunicationKey";

NS_ASSUME_NONNULL_BEGIN

NSDictionary * _Nonnull EventMap(void);

@interface MCRNEventEmitter: RCTEventEmitter<RCTBridgeModule>

/**
 发送消息通知至RN端
 
 @param notiName 和RN端一致的通知名称
 @param object 和通知一同传递的参数
 */
+ (void)postNotificationName:(NSString *)notiName object:(NSDictionary *)object;


@end

NS_ASSUME_NONNULL_END
