//
//  BookListViewController.m
//  Library-iOS
//
//  Created by Vitor Leonardi on 6/13/13.
//  Copyright (c) 2013 Vitor Leonardi. All rights reserved.
//

#import "BookListViewController.h"
#import "BookEditViewController.h"

@interface BookListViewController ()
{
    NSArray *arrayBooks;
}
@end

@implementation BookListViewController

- (id)initWithStyle:(UITableViewStyle)style
{
    self = [super initWithStyle:style];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];

    arrayBooks = @[@{@"Autor": @"Bernard Cornwell", @"Livro": @"O Arqueiro", @"Capa": @"images.jpeg"}, @{@"Autor": @"Bernard Cornwell", @"Livro": @"O Andarilho", @"Capa": @"images-1.jpeg"}, @{@"Autor": @"Bernard Cornwell", @"Livro": @"O Herege", @"Capa": @"images-2.jpeg"}, @{@"Autor": @"George R. Martin", @"Livro": @"Game of Thrones", @"Capa": @"images.jpeg"}, @{@"Autor": @"George R. Martin", @"Livro": @"Dance With Dragons", @"Capa": @"images-1.jpeg"}];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [arrayBooks count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"Cell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    
    [cell.textLabel setText:[arrayBooks[indexPath.row] valueForKey:@"Livro"]];
    [cell.detailTextLabel setText:[arrayBooks[indexPath.row] valueForKey:@"Autor"]];
    
    return cell;
}

#pragma mark - Table view delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [self.tableView deselectRowAtIndexPath:indexPath animated:YES];
    [self performSegueWithIdentifier:@"bookEditSegue" sender:arrayBooks[indexPath.row]];
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([[segue identifier] isEqualToString:@"bookEditSegue"]) {
        BookEditViewController *bookVC = [segue destinationViewController];
        [bookVC setBook:sender];
    }
}

@end
