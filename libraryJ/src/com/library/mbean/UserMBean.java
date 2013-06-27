package com.library.mbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.library.entity.User;
import com.library.util.FacesUtil;

@ManagedBean(name = "userMBean")
@SessionScoped
public class UserMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// captura a sessão do contexto criado pelo JavaServer Faces
	FacesContext fc = FacesContext.getCurrentInstance();

	HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);

	@ManagedProperty(value = "#{userBO}")

	private User user;

	private Boolean isAdmin = false;
	
	public UserMBean() {
		this.user = new User();
	}

	public String login() {
		try {
			//TODO logica de login com o server via JSON
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
			//TODO logica de cadastro de usuário no server JSON
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public void delete() {
		try {
			//TODO logica de deleção de usuário no server JSON
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
		Boolean exists =  false; //userBO.existsEmail(this.user);
		if (this.user.getId() == null && exists) {
			FacesMessage message = new FacesMessage("Este email ja esta cadastrado em nosso serviço!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", message);
		}
	}

	public void setUserBO(UserBO userBO) {
		this.userBO = userBO;
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
