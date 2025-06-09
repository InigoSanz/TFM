package com.iem.tfm.application.port.input;

import java.util.List;

import com.iem.tfm.domain.model.User;

/**
 * Puerto de entrada para el caso de uso de consulta de usuarios.
 * <p>
 * Define las operaciones necesarias para recuperar información de los usuarios
 * registrados en el sistema. Los resultados devueltos corresponden al modelo de
 * dominio {@link User}.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public interface UserGetInputPort {

	/**
	 * Recupera todos los usuarios registrados.
	 * 
	 * @return lista de objetos User
	 */
	public List<User> getAllusers();

	/**
	 * Recupera un usuario a partir de su identificador único.
	 * 
	 * @param id ID del usuario
	 * @return objeto User correspondiente al ID
	 */
	public User getUserById(String id);
}