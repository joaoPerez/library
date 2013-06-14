package com.library.persistence.impl;

import org.springframework.stereotype.Repository;

import com.library.entity.User;
import com.library.persistence.UserDAO;

@Repository("userDAO")
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO{

}
