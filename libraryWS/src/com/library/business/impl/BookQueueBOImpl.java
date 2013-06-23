package com.library.business.impl;

import java.util.List;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.business.BookQueueBO;
import com.library.entity.BookQueue;
import com.library.entity.xml.MessageReturn;

@Service("bookQueueBO")
@Path("/bookQueue")
public class BookQueueBOImpl extends GenericBOImpl<BookQueue> implements BookQueueBO{

	@Override
	public List<BookQueue> list() throws Exception {
		return list(BookQueue.class, null, null, null);
	}

	@Override
	@Transactional
	public MessageReturn addOnQueue(BookQueue bookQueue) throws Exception {
		MessageReturn libReturn = new MessageReturn();
		try {
			BookQueue queue = new BookQueue();
			queue.setId(bookQueue.getId());
			queue.setBook(bookQueue.getBook());
			queue.setUser(bookQueue.getUser());
			queue.setRenting(bookQueue.getRenting());
			saveGeneric(queue);
		} catch (Exception e) {
			e.printStackTrace();
			libReturn.setMessage(e.getMessage());
		}
		if (libReturn.getMessage() == null) {
			libReturn.setMessage("Adicionado na fila com sucesso!");
		}
		return libReturn;
	}

	@Override
	@Transactional
	public MessageReturn delete(Long id) {
		MessageReturn libReturn = new MessageReturn();
		try {
			BookQueue bookQueue = findById(BookQueue.class, id);
			remove(bookQueue);
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
	public BookQueue getById(Long id) {
		try {
			return findById(BookQueue.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
