package com.library.business.impl;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.library.business.AuthorBO;
import com.library.entity.Author;

@Service("authorBO")
@Path("/author")
public class AuthorBOImpl extends GenericBOImpl<Author> implements AuthorBO{

}
