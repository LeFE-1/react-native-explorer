//
//  MCRNEventEmitter.m
//  demo-ios
//
//  Created by rnexplorer on 2019/5/6.
//  Copyright © 2019 rnexplorer. All rights reserved.
//

#import "MCRNEventEmitter.h"

static NSDictionary *eventMap;
NSDictionary *EventMap() {
    eventMap = @{
                 @"COMPONENT_DID_APPEAR": @"componentDidAppear",
                 @"COMPONENT_DID_DISAPPEAR": @"componentDidDisappear",
                 @"COMPONENT_RECEIVE_RESULT": @"componentReceiveResult",
                 @"COMPONENT_DID_DESTROY": @"componentDidDestroy",
                 @"WEAK_SOCKET": @"WeakSocket",
                 @"MCRN_ROUTE_INFO": @"MCRNRouteInfo"
                 };
    return eventMap;
}

@interface MCRNEventEmitter ()

@end

@implementation MCRNEventEmitter

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(postMessage:(nonnull NSString *)notiName body:(nullable id)data) {
    if (notiName.length) {
        [[NSNotificationCenter defaultCenter] postNotificationName:notiName object:data];
    }
}

+ (void)postNotificationName:(NSString *)notiName object:(NSDictionary *)object {
    
    NSDictionary *msgDict = @{
                              @"eventName": @"WeakSocket",
                              @"body": @{
                                      @"name": notiName,
                                      @"body": object
                                      }
                              };
    [[NSNotificationCenter defaultCenter] postNotificationName:kMCRNEventInternalCommunicationKey object:msgDict];
    
}



#pragma mark -- Private

+ (BOOL)requiresMainQueueSetup {
    return true;
}

- (dispatch_queue_t)methodQueue {
    return dispatch_get_main_queue();
}


- (instancetype)init {
    self = [super init];
    if (self) {
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(didReceiveNoti:) name:kMCRNEventInternalCommunicationKey object:nil];
    }
    return self;
}

- (NSDictionary *)constantsToExport {
    return EventMap();
}

- (NSArray<NSString *> *)supportedEvents {
    return EventMap().allValues;
}

- (void)didReceiveNoti:(NSNotification *)noti {
    if ([noti.object isKindOfClass:[NSDictionary class]]) {
        NSDictionary *msgDict = (NSDictionary *)noti.object;
        [self sendEventWithName:msgDict[@"eventName"] body:msgDict[@"body"]];
    }
}

- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

@end

