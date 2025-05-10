package com.iem.tfm.domain.model;

import com.iem.tfm.domain.util.UserRoleEnum;

/**
 * Modelo de dominio para los usuarios.
 * 
 * Define las credenciales necesarias para acceder a la aplicación, también 
 * contiene el rol de acceso y el estado del usuario.
 * 
 * Referencia el id del empleade de quien es el usuario de la app.
 * 
 * @author Inigo
 * @version 1.0
 */
public class User {
	
	private Long id;
	private String username;
	private String password;
	private UserRoleEnum role;
	private boolean userActive;
	private Long employeeId; // La primera opción fue referenciar un Employee, pero con el id debería ser suficiente.
	
	public User(Long id, String username, String password, UserRoleEnum role, boolean userActive, Long employeeId) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.userActive = userActive;
		this.employeeId = employeeId;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public UserRoleEnum getRole() {
		return role;
	}

	public boolean isUserActive() {
		return userActive;
	}
	
	public Long getEmployeeId() {
		return employeeId;
	}
}