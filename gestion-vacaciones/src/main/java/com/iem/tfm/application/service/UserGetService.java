package com.iem.tfm.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.port.input.UserGetInputPort;
import com.iem.tfm.application.port.output.UserRepositoryOutputPort;
import com.iem.tfm.domain.model.User;

import lombok.extern.slf4j.Slf4j;

/**
 * Servicio de aplicación para la consulta de usuarios registrados.
 * <p>
 * Implementa el caso de uso definido en {@link UserGetInputPort} y accede
 * a los datos mediante el puerto de salida {@link UserRepositoryOutputPort}.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Service
@Slf4j
public class UserGetService implements UserGetInputPort {
	
	@Autowired
	UserRepositoryOutputPort userRepositoryOutput;
	
	/**
	 * Recupera todos los usuarios registrados en el sistema.
	 * 
	 * @return lista de objetos User
	 */
	@Override
	public List<User> getAllusers() {
		log.info("-> Obteniendo todos los usuarios <-");
		
		return userRepositoryOutput.findAll();
	}
	
	/**
	 * Recupera un usuario por su ID.
	 * 
	 * @param id ID del usuario
	 * @return objeto User correspondiente al ID
	 */
	@Override
	public User getUserById(String id) {
		log.info("-> Obteniendo un usuario <-");
		
		return userRepositoryOutput.findById(id);
	}
}