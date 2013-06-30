package com.library.mbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import com.library.entity.Author;
import com.library.entity.xml.MessageReturn;
import com.library.util.FacesUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

@SessionScoped
@ManagedBean(name = "authorMBean")
public class AuthorMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// captura a sessão do contexto criado pelo JavaServer Faces
	FacesContext fc = FacesContext.getCurrentInstance();

	HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
	
	private List<Author> authorList;

	private Author author;

	private Author[] selectedAuthors;

	Client client = null;

	String host = null;

	public AuthorMBean() {
		client = Client.create();
		this.author = new Author();
		Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
		if (request instanceof HttpServletRequest) {
			String[] str = ((HttpServletRequest) request).getRequestURL().toString().split("library");
			host = str[0];
		}
	}

	private void loadList(){
		WebResource webResource = client.resource(host + "libraryWS/author");
		
		authorList = webResource.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Author>>(){});
	}

	public String list() {
		
		loadList();
		
		return "/common/listAuthor.xhtml?faces-redirect=true";
	}

	public void newAuthor() {
		this.author = new Author();
	}

	public void edit() {
	}

	public String save() {
		MessageReturn ret = new MessageReturn();
		try {
			WebResource webResource = client.resource(host + "libraryWS/author");

			ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, author);

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
			for (Author author : selectedAuthors) {
				WebResource webResource = client.resource(host + "libraryWS/author/" + author.getId());

				ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).delete(ClientResponse.class);

				if (response.getStatus() != 201 && response.getStatus() != 200) {
					ret.setMessage("Failed : HTTP error code : " + response.getStatus());
					throw new Exception(ret.getMessage());
				}

				ret = response.getEntity(MessageReturn.class);
			}

			if (selectedAuthors.length > 1) {
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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Author[] getSelectedAuthors() {
		return selectedAuthors;
	}

	public void setSelectedAuthors(Author[] selectedAuthors) {
		this.selectedAuthors = selectedAuthors;
	}

	public List<Author> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(List<Author> authorList) {
		this.authorList = authorList;
	}

}
