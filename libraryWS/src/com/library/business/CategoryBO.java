package com.library.business;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.library.entity.Category;
import com.library.entity.xml.MessageReturn;

public interface CategoryBO extends GenericBO<Category> {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> list() throws Exception;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MessageReturn save(final Category category) throws Exception;
}
