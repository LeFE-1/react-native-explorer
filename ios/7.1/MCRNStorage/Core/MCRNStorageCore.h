//
//  MCRNStorageCore.h
//  MCRNStorage
//
//  Created by rnexplorer on 2019/5/13.
//  Copyright © 2019 rnexplorer. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface MCRNStorageCore : NSObject

+ (instancetype)shared;

- (void)setItem:(NSString *)item forKey:(NSString *)key;

- (nullable NSString *)getItemForKey:(NSString *)key;

- (NSArray<NSString *> *)getItemsForKeys:(NSArray<NSString *> *)keys;

- (void)removeItemsForKeys:(NSArray<NSString *> *)itemKeys;

- (void)clearAllData;

@end

NS_ASSUME_NONNULL_END
