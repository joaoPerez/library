package com.library.mbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import com.library.entity.Book;
import com.library.entity.xml.MessageReturn;
import com.library.util.FacesUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

@SessionScoped
@ManagedBean(name = "bookMBean")
public class BookMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// captura a sessão do contexto criado pelo JavaServer Faces
	FacesContext fc = FacesContext.getCurrentInstance();

	HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
	
	private List<Book> bookList;

	private Book book;

	private Book[] selectedBooks;

	Client client = null;

	String host = null;

	public BookMBean() {
		client = Client.create();
		this.book = new Book();
		Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
		if (request instanceof HttpServletRequest) {
			String[] str = ((HttpServletRequest) request).getRequestURL().toString().split("library");
			host = str[0];
		}
	}

	private void loadList(){
		WebResource webResource = client.resource(host + "libraryWS/book");
		
		bookList = webResource.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Book>>(){});
	}

	public String list() {
		
		loadList();
		
		return "/common/listBook.xhtml?faces-redirect=true";
	}

	public void newBook() {
		this.book = new Book();
	}

	public void edit() {
	}

	public String save() {
		MessageReturn ret = new MessageReturn();
		try {
			WebResource webResource = client.resource(host + "libraryWS/book");

			ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, book);

			if (response.getStatus() != 201 && response.getStatus() != 200) {
				ret.setMessage("Failed : HTTP error code : " + response.getStatus());
				throw new Exception(ret.getMessage());
			}

			ret = response.getEntity(MessageReturn.class);

			FacesUtil.showSuccessMessage(ret.getMessage());
			loadList();
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.showAErrorMessage(ret.getMessage());
		}
		return "";
	}

	public void delete() {
		MessageReturn ret = new MessageReturn();
		try {
			for (Book book : selectedBooks) {
				WebResource webResource = client.resource(host + "libraryWS/book/" + book.getId());

				ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).delete(ClientResponse.class);

				if (response.getStatus() != 201 && response.getStatus() != 200) {
					ret.setMessage("Failed : HTTP error code : " + response.getStatus());
					throw new Exception(ret.getMessage());
				}

				ret = response.getEntity(MessageReturn.class);
			}

			if (selectedBooks.length > 1) {
				FacesUtil.showSuccessMessage("Autores excluidos com sucesso!");
			} else {
				FacesUtil.showSuccessMessage(ret.getMessage());
			}
			loadList();
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.showAErrorMessage(ret.getMessage());
		}
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Book[] getSelectedBooks() {
		return selectedBooks;
	}

	public void setSelectedBooks(Book[] selectedBooks) {
		this.selectedBooks = selectedBooks;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

}
