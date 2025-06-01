package com.iem.tfm.application.port.output;

import java.util.List;

import com.iem.tfm.domain.model.User;

/**
 * Puerto de salida para operaciones de persistencia y consulta de {@link User}.
 * <p>
 * Define las acciones que la capa de aplicación necesita realizar sobre el repositorio
 * de usuarios. Este puerto será implementado por un adaptador en la capa de infraestructura.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public interface UserRepositoryOutputPort {
	
	/**
	 * Guarda un usuario en el sistema.
	 * 
	 * @param user objeto User a persistir
	 * @return ID generado del usuario guardado
	 */
	public String save(User user);
	
	/**
	 * Busca un usuario por su nombre de usuario.
	 * 
	 * @param username nombre de usuario
	 * @return objeto User correspondiente
	 */
	public User findByUsername(String username);
	
	/**
	 * Verifica si existe un usuario con el nombre de usuario especificado.
	 * 
	 * @param username nombre de usuario a comprobar
	 * @return {@code true} si existe, {@code false} si no
	 */
	public boolean existsByUsername(String username);
	
	/**
	 * Recupera todos los usuarios registrados en el sistema.
	 * 
	 * @return lista de objetos User
	 */
	public List<User> findAll();
	
	/**
	 * Busca un usuario por su identificador único.
	 * 
	 * @param id ID del usuario
	 * @return objeto User correspondiente
	 */
	public User findById(String id);
}