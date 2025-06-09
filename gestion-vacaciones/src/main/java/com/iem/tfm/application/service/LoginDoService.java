package com.iem.tfm.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.port.input.LoginDoInputPort;
import com.iem.tfm.application.port.output.UserRepositoryOutputPort;
import com.iem.tfm.domain.exception.UserDomainException;
import com.iem.tfm.domain.model.User;

import lombok.extern.slf4j.Slf4j;

/**
 * Servicio de aplicación para el inicio de sesión de usuarios.
 * <p>
 * Implementa el caso de uso definido en {@link LoginDoInputPort} y utiliza el
 * puerto de salida {@link UserRepositoryOutputPort} para recuperar y validar
 * las credenciales del usuario.
 * </p>
 * 
 * Este servicio comprueba:
 * <ul>
 * <li>Si el usuario existe.</li>
 * <li>Si el usuario está activo.</li>
 * <li>Si la contraseña proporcionada coincide.</li>
 * </ul>
 * 
 * @author Inigo
 * @version 1.0
 */
@Service
@Slf4j
public class LoginDoService implements LoginDoInputPort {

	@Autowired
	UserRepositoryOutputPort userRepositoryOutput;

	/**
	 * Valida las credenciales del usuario e inicia sesión si son correctas.
	 * 
	 * @param username nombre de usuario
	 * @param password contraseña en texto plano
	 * @return objeto User autenticado
	 * @throws UserDomainException si el usuario no existe, no está activo o la
	 *                             contraseña es incorrecta
	 */
	@Override
	public User login(String username, String password) {
		log.debug("-> Autenticando al usuario: {} <-", username);

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

		log.debug("-> Usuario autenticado correctamente: {} <-", username);

		return user;
	}
}