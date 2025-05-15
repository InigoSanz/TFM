package com.iem.tfm.application.port.input;

import com.iem.tfm.application.command.EmployeeRegisterCommand;

/**
 * Puerto de entrada para el caso de uso de registro de empleados.
 * 
 * Recibe los datos de encapsulados de {@link EmployeeRegisterCommand}.
 * 
 * @author Inigo
 * @version 1.0
 */
public interface EmployeeRegisterInputPort {
	
	public String employeeRegister(EmployeeRegisterCommand command);

}