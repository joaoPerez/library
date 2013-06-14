package com.library.business.impl;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.library.business.BookQueueBO;
import com.library.entity.BookQueue;

@Service("bookQueueBO")
@Path("/bookQueue")
public class BookQueueBOImpl extends GenericBOImpl<BookQueue> implements BookQueueBO{

}
