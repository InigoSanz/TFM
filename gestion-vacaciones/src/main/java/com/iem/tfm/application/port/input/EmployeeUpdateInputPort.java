package com.iem.tfm.application.port.input;

import com.iem.tfm.domain.command.EmployeeUpdateCommand;

/**
 * Puerto de entrada para la actualización de empleados.
 * <p>
 * Define la operación necesaria para actualizar los datos de un empleado
 * existente en el sistema.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public interface EmployeeUpdateInputPort {

	/**
	 * Actualiza los datos de un empleado existente en base a la información
	 * proporcionada en el comando.
	 *
	 * @param command objeto que contiene los datos actualizados del empleado
	 */
	public void updateEmployee(EmployeeUpdateCommand command);
}