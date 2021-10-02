//
//  MCRNStorage.h
//  MCRNStorage
//
//  Created by rnexplorer on 2019/5/13.
//  Copyright © 2019 rnexplorer. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface MCRNStorage : NSObject

+ (void)setItem:(NSString *)item forKey:(NSString *)key;

+ (NSString *)getItemForKey:(NSString *)key;

+ (NSArray<NSString *> *)getItemsForKeys:(NSArray<NSString *> *)keys;

+ (void)removeItemForKey:(NSString *)key;

+ (void)removeItemsForKeys:(NSArray<NSString *> *)keys;

@end

NS_ASSUME_NONNULL_END
  
