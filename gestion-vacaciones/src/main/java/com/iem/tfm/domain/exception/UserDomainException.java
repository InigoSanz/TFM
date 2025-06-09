package com.iem.tfm.domain.exception;

/**
 * Clase de excepciones del dominio para los usuarios.
 * 
 * Se usa para implementar restricciones específicas para la entidad
 * {@link com.iem.tfm.domain.model.User}.
 * 
 * @author Inigo
 * @version 1.0
 */
public class UserDomainException extends RuntimeException {

	private static final long serialVersionUID = 8123955678285741891L;

	public UserDomainException(String message) {
		super(message);
	}
}