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

	@Override
	@Transactional
	public MessageReturn delete(Long id) {
		MessageReturn libReturn = new MessageReturn();
		try {
			Category category = findById(Category.class, id);
			remove(category);
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
	public Category getById(Long id) {
		try {
			return findById(Category.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
