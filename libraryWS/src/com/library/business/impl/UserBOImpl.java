package com.library.business.impl;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.library.business.UserBO;
import com.library.entity.User;
import com.library.entity.xml.LibraryReturn;
import com.library.web.utils.Utils;

@Path("/user")
@Service("userBO")
public class UserBOImpl extends GenericBOImpl<User> implements UserBO, Serializable{

	private static final long serialVersionUID = -7181845834876407823L;
	
	public LibraryReturn save(User user) {
		LibraryReturn libReturn = new LibraryReturn();
		try {
			User u = new User();
			u.setAddress(user.getAddress());
			u.setName(user.getName());
			u.setAdmin(user.getAdmin());
			u.setDateOfBirth(Utils.stringToDate(user.getBirth(), false));
			u.setEmail(user.getEmail());
			saveGeneric(u);
		} catch (Exception e) {
			e.printStackTrace();
			libReturn.setText(e.getMessage());
		}
		if(libReturn.getText().isEmpty()){
			libReturn.setText("Usuário Cadastrado com sucesso!");
		}
		return libReturn;
	}

	public List<User> list() throws Exception {
		return list(User.class, null, null, null);
	}

}
