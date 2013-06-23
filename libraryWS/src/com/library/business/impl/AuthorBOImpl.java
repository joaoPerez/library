package com.library.business.impl;

import java.util.List;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.business.AuthorBO;
import com.library.entity.Author;
import com.library.entity.xml.MessageReturn;

@Path("/author")
@Service("authorBO")
public class AuthorBOImpl extends GenericBOImpl<Author> implements AuthorBO{

	@Override
	public List<Author> list() throws Exception {
		return list(Author.class, null, null, null);
	}

	@Override
	@Transactional
	public MessageReturn save(Author author) throws Exception {
		MessageReturn libReturn = new MessageReturn();
		try {
			Author auth = new Author();
			auth.setId(author.getId());
			auth.setName(author.getName());
			saveGeneric(auth);
		} catch (Exception e) {
			e.printStackTrace();
			libReturn.setMessage(e.getMessage());
		}
		if (libReturn.getMessage() == null) {
			libReturn.setMessage("Autor Cadastrado com sucesso!");
		}
		return libReturn;
	}

}
