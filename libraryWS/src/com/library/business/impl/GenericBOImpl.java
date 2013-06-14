package com.library.business.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.library.business.GenericBO;
import com.library.persistence.impl.GenericDAOImpl;

@Service("genericBO")
public class GenericBOImpl<T> implements GenericBO<T>{

	@Resource(name="genericDAO")
	private GenericDAOImpl<T> genericDAO;
	
	@SuppressWarnings("hiding")
	public <T> T findById(Class<T> type, Long id) throws Exception {
		return genericDAO.findById(type, id);
	}

	@SuppressWarnings("hiding")
	public <T> T findByParameter(Class<T> type, Map<String, Object> queryParans) throws Exception {
		return genericDAO.findByParameter(type,queryParans);
	}

	public List<T> listPaginated(final Class<T> type, int startRow, int pageSize, Map<String, Object> queryParans, Boolean orderBy, String orderByField) throws Exception {
		return genericDAO.listPaginated(type, startRow, pageSize, queryParans, orderBy, orderByField);
	}

	public List<T> list(final Class<T> type,  Map<String, Object> queryParans, Boolean orderBy, String orderByField) throws Exception {
		return genericDAO.list(type, queryParans, orderBy, orderByField);
	}

	public Integer count(Class<T> type, Map<String, Object> queryParans) throws Exception {
		return genericDAO.count(type, queryParans);
	}

	public T saveGeneric(T obj) throws Exception {
		return genericDAO.save(obj);
	}

	public Boolean remove(T obj) throws Exception {
		return genericDAO.remove(obj);
	}
}
