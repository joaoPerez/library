//
//  BookEditViewController.m
//  Library-iOS
//
//  Created by Vitor Leonardi on 6/13/13.
//  Copyright (c) 2013 Vitor Leonardi. All rights reserved.
//

#import "BookEditViewController.h"

@interface BookEditViewController ()
@property (weak, nonatomic) IBOutlet UILabel *lblBookTitle;
@property (weak, nonatomic) IBOutlet UILabel *lblAuthorName;
@property (weak, nonatomic) IBOutlet UIImageView *imgBookCover;
@end

@implementation BookEditViewController

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
    
    [self.lblBookTitle setText:self.book[@"Livro"]];
    [self.lblAuthorName setText:self.book[@"Autor"]];
//    NSString *pathToCover = [NSString stringWithFormat:@"%@", [[NSBundle mainBundle] pathForResource:self.book[@"Capa"] ofType:@"jpeg"]];
    [self.imgBookCover setImage:[UIImage imageNamed:self.book[@"Capa"]]];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
