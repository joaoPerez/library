//
//  LoginViewController.m
//  Library-iOS
//
//  Created by Vitor Leonardi on 6/27/13.
//  Copyright (c) 2013 Vitor Leonardi. All rights reserved.
//

#import "LoginViewController.h"

@interface LoginViewController () <UITextFieldDelegate>

@property (weak, nonatomic) IBOutlet UITextField *textUser;
@property (weak, nonatomic) IBOutlet UITextField *textPassword;
@property (weak, nonatomic) IBOutlet UIButton *btnDismissKeyboard;

- (IBAction)didClickAtConnect:(id)sender;
- (IBAction)didEndEditing:(id)sender;
- (IBAction)didEnterEditing:(id)sender;

@end

@implementation LoginViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)didClickAtConnect:(id)sender
{
    if ([self.textUser.text isEqualToString:@"admin"])
    {
        [[NSUserDefaults standardUserDefaults] setBool:YES forKey:@"admin"];
        self.navigationItem.rightBarButtonItem.enabled = YES;
    }
    else
    {
        [[NSUserDefaults standardUserDefaults] setBool:NO forKey:@"admin"];
        self.navigationItem.rightBarButtonItem.enabled = NO;
    }
    
    [self performSegueWithIdentifier:@"tabBarSegue" sender:self];
}

- (IBAction)didEnterEditing:(id)sender
{
    [self.btnDismissKeyboard setHidden:NO];
}

- (IBAction)didEndEditing:(id)sender
{
    [self.btnDismissKeyboard setHidden:YES];
    [self.textUser resignFirstResponder];
    [self.textPassword resignFirstResponder];
}

@end
