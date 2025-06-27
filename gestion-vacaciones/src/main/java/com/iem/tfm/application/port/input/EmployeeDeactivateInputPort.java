package com.iem.tfm.application.port.input;

/**
 * Puerto de entrada para la desactivación de empleados.
 * <p>
 * Define la operación necesaria para marcar a un empleado como inactivo en el
 * sistema.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public interface EmployeeDeactivateInputPort {

	/**
	 * Desactiva un empleado en el sistema a partir de su identificador.
	 *
	 * @param employeeId identificador único del empleado que se desea desactivar
	 */
	public void deactivateEmployee(String employeeId);
}