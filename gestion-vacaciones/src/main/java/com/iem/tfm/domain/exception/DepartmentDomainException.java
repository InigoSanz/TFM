package com.iem.tfm.domain.exception;

/**
 * Clase de excepciones del dominio para los departamentos.
 * 
 * Se usa para implementar restricciones específicas para la entidad {@link com.iem.tfm.domain.model.Department}.
 * 
 * @author Inigo
 * @version 1.0
 */
public class DepartmentDomainException extends RuntimeException {

	private static final long serialVersionUID = -4766671888147336389L;
	
	public DepartmentDomainException(String message) {
		super(message);
	}
}