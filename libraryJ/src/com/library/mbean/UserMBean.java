package com.library.mbean;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import com.library.entity.User;
import com.library.entity.xml.MessageReturn;
import com.library.util.FacesUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@SessionScoped
@ManagedBean(name = "userMBean")
public class UserMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// captura a sessão do contexto criado pelo JavaServer Faces
	FacesContext fc = FacesContext.getCurrentInstance();

	HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);

	private User user;

	private Boolean isAdmin = false;

	public UserMBean() {
		this.user = new User();
	}

	public String login() {
		try {
			// TODO logica de login com o server via JSON
		} catch (Exception e) {
			FacesUtil.exibirMensagemErro(e.getMessage());
		}

		return "../common/index.xhtml?faces-redirect=true";
	}

	public void newUser() {
		this.user = new User();
	}

	public String cancel() {
		return "index.xhtml\faces-redirect=true";
	}

	public String save() {
		try {
			Client client = Client.create();

			WebResource webResource = client.resource("http://www.mconnti.com:8080/libraryWS/user");
			user.setAdmin(false);
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, user);

			if (response.getStatus() != 201) {
				System.out.println("Failed : HTTP error code : " + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			MessageReturn ret = response.getEntity(MessageReturn.class);
			System.out.println(ret.getMessage());
			FacesMessage message = new FacesMessage(ret.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			FacesContext.getCurrentInstance().addMessage("", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public void delete() {
		try {
			// TODO logica de deleção de usuário no server JSON
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkEmail() throws ValidatorException {
		String digitado = this.user.getEmail();
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(digitado);
		boolean matchFound = m.matches();

		if (!matchFound) {
			FacesMessage message = new FacesMessage("Email invalido, favor verificar");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", message);
		}
	}

	public void existsEmail() throws ValidatorException {
		String email = this.user.getEmail();
		this.user.setEmail(email);
		Boolean exists = false; // userBO.existsEmail(this.user);
		if (this.user.getId() == null && exists) {
			FacesMessage message = new FacesMessage("Este email ja esta cadastrado em nosso serviço!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", message);
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
