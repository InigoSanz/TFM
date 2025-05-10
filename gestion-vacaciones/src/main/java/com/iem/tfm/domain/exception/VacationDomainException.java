package com.iem.tfm.domain.exception;

/**
 * Clase de excepciones del dominio para las vacaciones.
 * 
 * Se usa para implementar restricciones específicas para la entidad {@link com.iem.tfm.domain.model.Vacation}.
 * 
 * @author Inigo
 * @version 1.0
 */
public class VacationDomainException extends RuntimeException {
	
	private static final long serialVersionUID = 3792815060210298581L;

	public VacationDomainException(String message) {
		super(message);
	}
}