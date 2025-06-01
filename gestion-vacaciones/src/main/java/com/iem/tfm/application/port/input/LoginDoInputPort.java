package com.iem.tfm.application.port.input;

import com.iem.tfm.domain.model.User;

/**
 * Puerto de entrada para el caso de uso de autenticación de usuarios.
 * <p>
 * Define la operación necesaria para validar las credenciales de un usuario
 * e iniciar sesión en el sistema. Devuelve una instancia del modelo de dominio {@link User}
 * si la autenticación es exitosa.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public interface LoginDoInputPort {
	
	/**
	 * Valida las credenciales del usuario e inicia sesión.
	 * 
	 * @param username nombre de usuario
	 * @param password contraseña en texto plano
	 * @return objeto User autenticado
	 */
	public User login(String username, String password);
}