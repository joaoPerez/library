package com.library.business.impl;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.library.business.UserBO;
import com.library.entity.User;

@Service("userBO")
@Path("/user")
public class UserBOImpl extends GenericBOImpl<User> implements UserBO{

}
