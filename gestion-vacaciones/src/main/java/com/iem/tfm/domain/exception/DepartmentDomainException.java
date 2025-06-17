package com.iem.tfm.domain.exception;

/**
 * Excepción específica del dominio para la entidad
 * {@link com.iem.tfm.domain.model.Department}.
 * <p>
 * Se lanza cuando se incumplen reglas de negocio relacionadas con los
 * departamentos.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public class DepartmentDomainException extends RuntimeException { // Cambiar todas las excepciones definidas por mi a
																	// Exception (así incluye checked exception)

	private static final long serialVersionUID = -4766671888147336389L;

	/**
	 * Crea una nueva excepción de dominio con un mensaje descriptivo.
	 *
	 * @param message detalle del error de dominio
	 */
	public DepartmentDomainException(String message) {
		super(message);
	}
}