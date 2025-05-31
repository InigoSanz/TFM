package com.iem.tfm.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.port.input.UserGetInputPort;
import com.iem.tfm.application.port.output.UserRepositoryOutputPort;
import com.iem.tfm.domain.model.User;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Service
@Slf4j
public class UserGetService implements UserGetInputPort {
	
	@Autowired
	UserRepositoryOutputPort userRepositoryOutput;

	@Override
	public List<User> getAllusers() {
		log.info("-> Obteniendo todos los usuarios <-");
		
		return userRepositoryOutput.findAll();
	}

	@Override
	public User getUserById(String id) {
		log.info("-> Obteniendo un usuario <-");
		
		return userRepositoryOutput.findById(id);
	}
}