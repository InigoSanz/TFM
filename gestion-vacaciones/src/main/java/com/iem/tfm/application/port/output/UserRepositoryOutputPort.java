package com.iem.tfm.application.port.output;

import java.util.List;

import com.iem.tfm.domain.model.User;

/**
 * 
 */
public interface UserRepositoryOutputPort {
	
	public String save(User user);
	
	public User findByUsername(String username);
	
	public boolean existsByUsername(String username);

	public List<User> findAll();

	public User findById(String id);
}