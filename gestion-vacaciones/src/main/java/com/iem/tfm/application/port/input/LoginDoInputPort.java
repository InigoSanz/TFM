package com.iem.tfm.application.port.input;

import com.iem.tfm.domain.model.User;

/**
 * 
 */
public interface LoginDoInputPort {
	
	public User login(String username, String password);
}