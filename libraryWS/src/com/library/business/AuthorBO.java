package com.library.business;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import com.library.entity.Author;

public interface AuthorBO extends GenericBO<Author> {

	@GET
	@Produces("text/xml")
	public List<Author> list(final Class<Author> Author, Map<String, Object> queryParans, Boolean orderBy, String orderByField) throws Exception;
}
