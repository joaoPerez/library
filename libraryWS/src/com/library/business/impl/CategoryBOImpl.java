package com.library.business.impl;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.library.business.CategoryBO;
import com.library.entity.Category;

@Service("categoryBO")
@Path("/category")
public class CategoryBOImpl extends GenericBOImpl<Category> implements CategoryBO{

}
