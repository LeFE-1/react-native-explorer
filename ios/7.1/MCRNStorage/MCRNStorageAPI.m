//
//  MCRNStorageAPI.m
//  MCRNStorage
//
//  Created by rnexplorer on 2019/5/13.
//  Copyright © 2019 rnexplorer. All rights reserved.
//

#import "MCRNStorageAPI.h"
#import "MCRNStorageCore.h"
#import <React/RCTBridgeModule.h>

@interface MCRNStorageAPI ()<RCTBridgeModule>

@end

@implementation MCRNStorageAPI

RCT_EXPORT_MODULE(MCRNStorage)

- (dispatch_queue_t)methodQueue {
    return dispatch_queue_create("MCRNStorageAPIQueue", DISPATCH_QUEUE_SERIAL);
}


RCT_EXPORT_METHOD(setItem:(NSString *)itemKey andItem:(NSString *)item) {
    [[MCRNStorageCore shared] setItem:item forKey:itemKey];
}

RCT_EXPORT_METHOD(getItem:(NSString *)itemKey
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject) {
    NSString *item = [[MCRNStorageCore shared] getItemForKey:itemKey];
    resolve(item);
}

RCT_EXPORT_METHOD(getItems:(NSArray<NSString *> *)itemKeys
                  callback:(RCTResponseSenderBlock)callback) {
    NSMutableArray *arrM = @[].mutableCopy;
    for (NSString *key in itemKeys) {
        [arrM addObject:[[MCRNStorageCore shared] getItemForKey:key]];
    }
    callback(arrM.copy);
}

RCT_EXPORT_METHOD(removeItem:(NSString *)itemKey) {
    [[MCRNStorageCore shared] removeItemsForKeys:@[itemKey]];
}

RCT_EXPORT_METHOD(removeItems:(NSArray<NSString *> *)itemKeys) {
     [[MCRNStorageCore shared] removeItemsForKeys:itemKeys];
}

RCT_EXPORT_METHOD(clearAllData) {
     [[MCRNStorageCore shared] clearAllData];
}

RCT_EXPORT_METHOD(saveMsg:(NSArray *)name msg:(NSDictionary *)msg callback:(RCTResponseSenderBlock)callback) {
     
  
  callback(@[name, msg]);
}


@end
