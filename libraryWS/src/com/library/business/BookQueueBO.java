package com.library.business;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.library.entity.BookQueue;
import com.library.entity.xml.MessageReturn;

public interface BookQueueBO extends GenericBO<BookQueue> {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BookQueue> list() throws Exception;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MessageReturn addOnQueue(final BookQueue bookQueue) throws Exception;
	
	@DELETE @Path("{id}")
	public MessageReturn delete (@PathParam("id") Long id);
	
	@GET @Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public BookQueue getById(@PathParam("id") Long id);


}
