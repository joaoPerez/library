package com.library.business.impl;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.business.UserBO;
import com.library.entity.User;
import com.library.entity.xml.MessageReturn;
import com.library.web.utils.Utils;

@Path("/user")
@Service("userBO")
public class UserBOImpl extends GenericBOImpl<User> implements UserBO, Serializable {

	private static final long serialVersionUID = -7181845834876407823L;

	@Override
	@Transactional
	public MessageReturn save(User user) {
		MessageReturn libReturn = new MessageReturn();
		try {
			User u = new User();
			u.setId(user.getId());
			u.setAddress(user.getAddress());
			u.setName(user.getName());
			u.setAdmin(user.getAdmin());
			u.setBirthDate(Utils.stringToDate(user.getBirth(), false));
			u.setEmail(user.getEmail());
			saveGeneric(u);
		} catch (Exception e) {
			e.printStackTrace();
			libReturn.setMessage(e.getMessage());
		}
		if (libReturn.getMessage() == null && user.getId() == null) {
			libReturn.setMessage("Usuário Cadastrado com sucesso!");
		} else if (libReturn.getMessage() == null && user.getId() != null){
			libReturn.setMessage("Usuário alterado com sucesso!");
		}
		return libReturn;
	}

	@Override
	public List<User> list() throws Exception {
		return list(User.class, null, null, null);
	}

}
