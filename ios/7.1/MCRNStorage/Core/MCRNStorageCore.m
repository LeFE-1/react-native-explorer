//
//  MCRNStorageCore.m
//  MCRNStorage
//
//  Created by rnexplorer on 2019/5/13.
//  Copyright © 2019 rnexplorer. All rights reserved.
//

#import "MCRNStorageCore.h"

NSString *MCRNHandleKeys(NSString *key) {
    return [NSString stringWithFormat:@"MCRNKey-%@", key];
}

@interface MCRNStorageCore()

@property(nonatomic, strong) NSUserDefaults *userDefaults;

@end

@implementation MCRNStorageCore

- (NSUserDefaults *)userDefaults {
    return [NSUserDefaults standardUserDefaults];
}

+ (instancetype)shared {
    static id obj = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        obj = [[self alloc] init];
    });
    return obj;
}

- (void)setItem:(NSString *)item forKey:(NSString *)key {
    if (item && key.length) {
        [self.userDefaults setValue:item forKey:MCRNHandleKeys(key)];
        [self.userDefaults synchronize];
    }
}

- (nullable NSString *)getItemForKey:(NSString *)key {
    if (key.length) {
        return [self.userDefaults valueForKey:MCRNHandleKeys(key)];
    }
    return nil;
}

- (NSArray<NSString *> *)getItemsForKeys:(NSArray<NSString *> *)keys {

    NSMutableArray *arrM = @[].mutableCopy;
    
    for (NSString *key in keys) {
        if (key.length) {
            NSString *item = [self.userDefaults valueForKey:MCRNHandleKeys(key)];
            if (item) {
                [arrM addObject:item];
            }
        }
    }
    return arrM.copy;
}

- (void)removeItemsForKeys:(NSArray<NSString *> *)itemKeys {
    
    for (NSString *itemKey in itemKeys) {
        if (itemKey.length) {
            [self.userDefaults removeObjectForKey:MCRNHandleKeys(itemKey)];
        }
    }
    [self.userDefaults synchronize];
}

- (void)clearAllData {
  NSDictionary *dict = [self.userDefaults dictionaryRepresentation];
  for(NSString *key in dict) {
    if ([key hasPrefix:@"MCRNKey"] ) {
      [self.userDefaults removeObjectForKey: key];
    }
  }
  [self.userDefaults synchronize];
}



@end
