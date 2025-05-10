package com.iem.tfm.domain.exception;

/**
 * Clase de excepciones del dominio para los empleados.
 * 
 * Se usa para implementar restricciones específicas para la entidad {@link com.iem.tfm.domain.model.Employee}.
 */
public class EmployeeDomainException extends RuntimeException {

	private static final long serialVersionUID = -441547561376566175L;
	
	public EmployeeDomainException(String message) {
		super(message);
	}
}