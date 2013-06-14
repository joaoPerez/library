package com.library.business.impl;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.library.business.BookBO;
import com.library.entity.Book;

@Service("bookBO")
@Path("/book")
public class BookBOImpl extends GenericBOImpl<Book> implements BookBO{

}
