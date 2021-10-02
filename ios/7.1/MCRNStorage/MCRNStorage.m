//
//  MCRNStorage.m
//  MCRNStorage
//
//  Created by rnexplorer on 2019/5/13.
//  Copyright © 2019 rnexplorer. All rights reserved.
//

#import "MCRNStorage.h"
#import "MCRNStorageCore.h"

@implementation MCRNStorage

+ (void)setItem:(NSString *)item forKey:(NSString *)key {
    [[MCRNStorageCore shared] setItem:item forKey:key];
}

+ (NSString *)getItemForKey:(NSString *)key {
    return [[MCRNStorageCore shared] getItemForKey:key];
}

+ (NSArray<NSString *> *)getItemsForKeys:(NSArray<NSString *> *)keys {
    return [[MCRNStorageCore shared] getItemsForKeys:keys];
}

+ (void)removeItemsForKeys:(NSArray<NSString *> *)keys {
    [[MCRNStorageCore shared] removeItemsForKeys:keys];
}

+ (void)removeItemForKey:(NSString *)key {
    [[MCRNStorageCore shared] removeItemsForKeys:@[key]];
}


@end
