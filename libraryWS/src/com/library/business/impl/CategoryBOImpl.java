package com.library.business.impl;

import java.util.List;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.business.CategoryBO;
import com.library.entity.Category;
import com.library.entity.xml.MessageReturn;

@Service("categoryBO")
@Path("/category")
public class CategoryBOImpl extends GenericBOImpl<Category> implements CategoryBO{

	@Override
	public List<Category> list() throws Exception {
		return list(Category.class, null, null, null);
	}

	@Override
	@Transactional
	public MessageReturn save(Category category) throws Exception {
		MessageReturn libReturn = new MessageReturn();
		try {
			Category cat = new Category();
			cat.setId(category.getId());
			cat.setBookList(category.getBookList());
			cat.setType(category.getType());
			saveGeneric(cat);
		} catch (Exception e) {
			e.printStackTrace();
			libReturn.setMessage(e.getMessage());
		}
		if (libReturn.getMessage() == null && category.getId() == null) {
			libReturn.setMessage("Categoria cadastrada com sucesso!");
		} else if (libReturn.getMessage() == null && category.getId() != null){
			libReturn.setMessage("Categoria alterada com sucesso!");
		}
		return libReturn;
	}

}
