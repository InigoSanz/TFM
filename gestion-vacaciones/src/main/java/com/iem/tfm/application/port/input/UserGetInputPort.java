package com.iem.tfm.application.port.input;

import java.util.List;

import com.iem.tfm.domain.model.User;

/**
 * 
 */
public interface UserGetInputPort {
	
	public List<User> getAllusers();
	
	public User getUserById(String id);
}