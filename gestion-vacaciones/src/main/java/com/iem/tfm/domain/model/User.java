package com.iem.tfm.domain.model;

import com.iem.tfm.domain.exception.UserDomainException;
import com.iem.tfm.domain.util.PasswordRules;

import lombok.Builder;
import lombok.Getter;

/**
 * Modelo de dominio para los usuarios.
 * 
 * Define las credenciales necesarias para acceder a la aplicación, también
 * contiene el rol de acceso y el estado del usuario.
 * 
 * Referencia el id del empleade de quien es el usuario de la app.
 * 
 * @author Inigo
 * @version 1.1
 */
@Getter
@Builder
public class User {

	private String id;
	private String username;
	private String password;
	private UserRoleEnum role;
	private boolean userActive;
	private String employeeId; // La primera opción fue referenciar un Employee, pero con el id debería ser
								// suficiente.

	public User(String id, String username, String password, UserRoleEnum role, boolean userActive, String employeeId) {

		if (username == null) {
			throw new UserDomainException("El nombre de usuario no puede ser nulo.");
		}

		if (!PasswordRules.isPasswordValid(password)) {
			throw new UserDomainException(
					"La contraseña debe tener mínimo 8 caracteres, una letra mayúscula y un número.");
		}

		if (role == null) {
			throw new UserDomainException("Debe tener un rol para usar la aplicación.");
		}

		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.userActive = userActive;
		this.employeeId = employeeId;
	}
}