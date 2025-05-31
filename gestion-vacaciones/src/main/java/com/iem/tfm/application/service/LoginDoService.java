package com.iem.tfm.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.port.input.LoginDoInputPort;
import com.iem.tfm.application.port.output.UserRepositoryOutputPort;
import com.iem.tfm.domain.exception.UserDomainException;
import com.iem.tfm.domain.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginDoService implements LoginDoInputPort {
	
	@Autowired
    UserRepositoryOutputPort userRepositoryOutput;

	@Override
	public User login(String username, String password) {
		log.info("-> Autenticando al usuario: {} <-", username);
		
		User user = userRepositoryOutput.findByUsername(username);
		
		if (user == null) {
			throw new UserDomainException("Usuario no encontrado con username: " + username);
		}
		
		if (!user.isUserActive()) {
			throw new UserDomainException("El usuario no esta activado.");
		}
		
		if (!user.getPassword().equals(password)) {
            throw new UserDomainException("Contraseña incorrecta.");
        }
		
		log.info("-> Usuario autenticado correctamente: {} <-", username);
		
		return user;
	}
}