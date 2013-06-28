package com.library.business.impl;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.business.UserBO;
import com.library.entity.User;
import com.library.entity.xml.MessageReturn;
import com.library.persistence.UserDAO;
import com.library.web.utils.Utils;

@Path("/user")
@Service("userBO")
public class UserBOImpl extends GenericBOImpl<User> implements UserBO, Serializable {

	private static final long serialVersionUID = -7181845834876407823L;
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public MessageReturn save(User user) {
		MessageReturn libReturn = new MessageReturn();
		User u = null;
		try {
			u = new User();
			u.setId(user.getId());
			u.setAddress(user.getAddress());
			u.setName(user.getName());
			u.setAdmin(user.getAdmin());
			if(user.getBirth() != null){
				u.setBirthDate(Utils.stringToDate(user.getBirth(), false));
			} else {
				u.setBirthDate(user.getBirthDate());
			}			
			u.setEmail(user.getEmail());
			u.setPassword(user.getPassword());
			saveGeneric(u);
		} catch (Exception e) {
			e.printStackTrace();
			libReturn.setUser(u);
			libReturn.setMessage(e.getMessage());
		}
		if (libReturn.getMessage() == null && user.getId() == null) {
			libReturn.setMessage("Usuário Cadastrado com sucesso!");
			libReturn.setUser(u);
		} else if (libReturn.getMessage() == null && user.getId() != null){
			libReturn.setMessage("Usuário alterado com sucesso!");
			libReturn.setUser(u);
		}
		return libReturn;
	}

	@Override
	public List<User> list() throws Exception {
		return list(User.class, null, null);
	}

	@Override
	@Transactional
	public MessageReturn delete(Long id) {
		MessageReturn libReturn = new MessageReturn();
		try {
			User user = findById(User.class, id);
			remove(user);
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
	public User getById(Long id) {
		try {
			return findById(User.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public MessageReturn login(User user) {
		MessageReturn messageReturn = userDAO.getByEmail(user.getEmail());
		if (!messageReturn.getUser().getPassword().equals(user.getPassword())) {
			messageReturn.setUser(null);
			messageReturn.setMessage("Senha informada esta incorreta!");
		}else{
			messageReturn.setMessage("Login realizado com sucesso!");
		}
		return messageReturn;
	}
}
