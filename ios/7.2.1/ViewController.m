//
//  ViewController.m
//  7.2.1
//
//  Created by Yanda Zhao on 2020/1/3.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "ViewController.h"
#import <JavaScriptCore/JavaScriptCore.h>


@interface MCAlertView: UIAlertView
  
@property (strong, nonatomic) JSContext *ctxt;
//@property (strong, nonatomic) JSManagedValue *successHandler;
//@property (strong, nonatomic) JSManagedValue *failureHandler;
  
@property (strong, nonatomic) JSValue *successHandler;
@property (strong, nonatomic) JSValue *failureHandler;
  
- (id)initWithTitle:(NSString *)title
            message:(NSString *)message
            success:(JSValue *)successHandler
            failure:(JSValue *)failureHandler
            context:(JSContext *)context;
  
@end

@implementation MCAlertView

- (id)initWithTitle:(NSString *)title
            message:(NSString *)message
            success:(JSValue *)successHandler
            failure:(JSValue *)failureHandler
            context:(JSContext *)context {
  
  self = [super initWithTitle:title
                      message:message
                     delegate:self
            cancelButtonTitle:@"No"
            otherButtonTitles:@"Yes", nil];
  
  if (self) {
    _ctxt = context;
    _successHandler = successHandler;
    _failureHandler = failureHandler;
    
//    _successHandler = [JSManagedValue managedValueWithValue:successHandler];
//    // A JSManagedValue by itself is a weak reference. You convert it into a conditionally retained
//    // reference, by inserting it to the JSVirtualMachine using addManagedReference:withOwner:
//    [context.virtualMachine addManagedReference:_successHandler withOwner:self];
//
//    _failureHandler = [JSManagedValue managedValueWithValue:failureHandler];
//    [context.virtualMachine addManagedReference:_failureHandler withOwner:self];
  }
  return self;
}
  
- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex {
  if (buttonIndex == self.cancelButtonIndex) {
//    JSValue *function = [self.failureHandler value];
    JSValue *function = self.failureHandler;
    [function callWithArguments:@[]];
  } else {
//    JSValue *function = [self.successHandler value];
    JSValue *function = self.successHandler;
    [function callWithArguments:@[]];
  }

//  [self.ctxt.virtualMachine removeManagedReference:_failureHandler withOwner:self];
//  [self.ctxt.virtualMachine removeManagedReference:_successHandler withOwner:self];
}
  
  - (void)dealloc {
    NSLog(@"alertView 销毁了");
  }

@end

@interface ViewController ()
  
@property (nonatomic, strong) JSContext *context;
  
  @property (nonatomic, strong) JSContext *context2;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
  
  
  self.context = [[JSContext alloc] init];
  [self.context setExceptionHandler:^(JSContext *context, JSValue *exception) {
    NSLog(@"context: %@, exception: %@", context, exception);
  }];
  
}
  
- (IBAction)getObjectFromJS {
  JSContext *context = [[JSContext alloc] init];
  [context evaluateScript:@"var foo = { bar: 'hello world' }"];
  
  JSValue *foo = context[@"foo"];
  NSLog(@"%@", [foo toDictionary]); // { bar = hello world; }
}
  
- (IBAction)showNativeAlert {
  
  self.context[@"presentNativeAlert"] = ^(NSString *title,
                                          NSString *message,
                                          JSValue *success,
                                          JSValue *failure) {
    JSContext *context = [JSContext currentContext];
    MCAlertView *alertView = [[MCAlertView alloc] initWithTitle:title
                                                        message:message
                                                        success:success
                                                        failure:failure
                                                        context:context];
    [alertView show];
  };
  
  
  self.context[@"print"] = ^(NSString *message) {
    NSLog(@"JS: %@", message);
  };
  
  NSString *jsCode = @" \
  presentNativeAlert( \
  'title', \
  'message', \
  function() { \
  print('点击确定') \
  }, \
  function() { \
  print('点击取消') \
  }) \
  ";
  [self.context evaluateScript: jsCode];
  
}
  
  


@end
