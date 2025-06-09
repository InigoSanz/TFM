package com.iem.tfm.domain.exception;

/**
 * Clase de excepciones del dominio para los empleados.
 * <p>
 * Se usa para validar reglas de negocio de la entidad
 * {@link com.iem.tfm.domain.model.Employee}.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public class EmployeeDomainException extends RuntimeException {

	private static final long serialVersionUID = -441547561376566175L;

	/**
	 * Constructor que recibe un mensaje de error personalizado.
	 * 
	 * @param message mensaje explicando el motivo
	 */
	public EmployeeDomainException(String message) {
		super(message);
	}
}