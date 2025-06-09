package com.iem.tfm.application.port.input;

import com.iem.tfm.domain.command.EmployeeRegisterCommand;

/**
 * Puerto de entrada para el caso de uso de registro de empleados.
 * <p>
 * Define la operación que debe implementar el servicio de aplicación. Recibe
 * los datos encapsulados en {@link EmployeeRegisterCommand}.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public interface EmployeeRegisterInputPort {

	/**
	 * Registra un nuevo empleado a partir de los datos del Command.
	 * 
	 * @param command Objeto que contiene todos los datos del nuevo empleado
	 * @return ID del empleado registrado
	 */
	public String employeeRegister(EmployeeRegisterCommand command);
}