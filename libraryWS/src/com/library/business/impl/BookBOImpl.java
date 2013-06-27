package com.library.business.impl;

import java.util.List;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.business.BookBO;
import com.library.entity.Book;
import com.library.entity.xml.MessageReturn;
import com.library.entity.xml.SearchObject;

@Service("bookBO")
@Path("/book")
public class BookBOImpl extends GenericBOImpl<Book> implements BookBO{

	@Override
	public List<Book> list()  {
		try {
			return list(Book.class, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public MessageReturn save(Book book)  {
		MessageReturn libReturn = new MessageReturn();
		try {
			Book b = new Book();
			b.setId(book.getId());
			b.setAvailable(book.getAvailable());
			b.setAuthor(book.getAuthor());
			b.setDescription(book.getDescription());
			b.setTitle(book.getTitle());
			b.setYearOfPublished(book.getYearOfPublished());
			b.setCategory(book.getCategory());
			saveGeneric(b);
		} catch (Exception e) {
			e.printStackTrace();
			libReturn.setMessage(e.getMessage());
		}
		if (libReturn.getMessage() == null && book.getId() == null) {
			libReturn.setMessage("Livro cadastrado com sucesso!");
		} else if (libReturn.getMessage() == null ){
			libReturn.setMessage("Livro editado com sucesso!");
		}
		return libReturn;
	}

	@Override
	@Transactional
	public MessageReturn delete(Long id) {
		MessageReturn libReturn = new MessageReturn();
		try {
			Book book = findById(Book.class, id);
			remove(book);
		} catch (Exception e) {
			e.printStackTrace();
			libReturn.setMessage(e.getMessage());
		}
		if (libReturn.getMessage() == null) {
			libReturn.setMessage("Removido com sucesso!");
		} 
		return libReturn;
	}

	@Override
	public Book getById(Long id) {
		try {
			return findById(Book.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> serchedList(SearchObject serachObject) {
		try {
			return list(Book.class, serachObject.getQueryParams(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
