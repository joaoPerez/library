package com.library.business.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.business.BookQueueBO;
import com.library.entity.Book;
import com.library.entity.BookQueue;
import com.library.entity.User;
import com.library.entity.xml.MessageReturn;
import com.library.entity.xml.SearchObject;
import com.library.persistence.BookDAO;
import com.library.persistence.UserDAO;

@Service("bookQueueBO")
@Path("/bookQueue")
public class BookQueueBOImpl extends GenericBOImpl<BookQueue> implements BookQueueBO {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private BookDAO bookDAO;

	@Override
	public List<BookQueue> list() throws Exception {
		return list(BookQueue.class, null, null);
	}

	@Override
	@Transactional
	public MessageReturn addOnQueue(BookQueue bookQueue) throws Exception {
		MessageReturn libReturn = new MessageReturn();
		try {
			BookQueue queue = new BookQueue();
			Book book = bookDAO.findById(Book.class, bookQueue.getBook().getId());
			User user = userDAO.findById(User.class, bookQueue.getUser().getId());
			queue.setBook(book);
			queue.setUser(user);
			queue.setDateIn(new Date());
			queue.setRenting(false);
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

	@Override
	@Transactional
	public MessageReturn renting(BookQueue bookQueue) {
		MessageReturn messageReturn = new MessageReturn();
		try {
			Book book = bookDAO.findById(Book.class, bookQueue.getBook().getId());
			User user = userDAO.findById(User.class, bookQueue.getUser().getId());

			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("user", "= " + user.getId());
			queryParams.put("renting", "= true");

			List<BookQueue> list = list(BookQueue.class, queryParams, null);

			if (list.size() > 0) {
				messageReturn.setMessage("É permitida a locação de somente um livro por vez.");
				throw new Exception(messageReturn.getMessage());
			}

			if (book.getAvailable()) {
				book.setAvailable(false);
				bookDAO.save(book);

				user.getBookList().add(book);
				userDAO.save(user);

				BookQueue queue = new BookQueue();
				queue.setBook(book);
				queue.setUser(user);
				queue.setDateIn(new Date());
				queue.setRenting(true);
				saveGeneric(queue);
				messageReturn.setBookQueue(bookQueue);
			} else {
				messageReturn.setMessage("O livro não está disponível.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			messageReturn.setMessage(e.getMessage());
		}

		if (messageReturn.getMessage() == null || messageReturn.getMessage().isEmpty()) {
			messageReturn.setMessage("Aluguel realizado com sucesso.");
		}

		return messageReturn;
	}

	@Override
	@Transactional
	public MessageReturn waitList(SearchObject searchObject) {
		MessageReturn messageReturn = new MessageReturn();
		try {
			searchObject.getQueryParams().put("renting", " = false");
			List<BookQueue> list = list(BookQueue.class, searchObject.getQueryParams(), null);
			if (list.size() == 0) {
				messageReturn.setMessage("Você é o próximo da fila, deseja aguardar.");
			} else {
				messageReturn.setMessage("A fila de espera deste livro é de: " + list.size() + " usuário(s), deseja aguardar.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messageReturn;
	}

	@Override
	@Transactional
	public MessageReturn releaseBook(BookQueue bookQueue) {
		MessageReturn messageReturn = new MessageReturn();
		Book book = null;
		try {
			book = bookDAO.findById(Book.class, bookQueue.getBook().getId());

			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("book", "= " + book.getId());
			queryParams.put("renting", "= true");
			bookQueue = findByParameter(BookQueue.class, queryParams);

			remove(bookQueue);

			queryParams.clear();
			queryParams.put("book", "= " + book.getId());
			queryParams.put("renting", "= false");

			List<BookQueue> queueList = list(BookQueue.class, queryParams, "id");

			if (queueList.isEmpty()) {
				book.setAvailable(true);
				bookDAO.save(book);
				messageReturn.setBook(book);
				messageReturn.setMessage(book.getTitle() + ", devolução efetuada com sucesso.");
			} else {
				for (BookQueue bQueue : queueList) {
					//testa para ver se usuário ja esta locando
					queryParams.clear();
					queryParams.put("user", "= " + bQueue.getUser().getId());
					queryParams.put("renting", "= true");
					
					List<BookQueue> list = list(BookQueue.class, queryParams, null);
					if(list.isEmpty()){
						bQueue.setRenting(true);
						saveGeneric(bQueue);
						
						bQueue.getUser().getBookList().add(book);
						userDAO.save(bQueue.getUser());
						messageReturn.setBook(book);
						messageReturn.setMessage("Este livro está liberado para o próximo da fila que é o(a) "+bQueue.getUser().getName());
						break;
					}
				}				
			}

		} catch (Exception e) {
			e.printStackTrace();
			messageReturn.setMessage(e.getMessage());
		}

		return messageReturn;
	}

}
