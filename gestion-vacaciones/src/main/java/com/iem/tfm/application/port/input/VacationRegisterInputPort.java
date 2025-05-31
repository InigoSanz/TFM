package com.iem.tfm.application.port.input;

import com.iem.tfm.application.command.VacationRegisterCommand;

/**
 * Puerto de entrada para el caso de uso de registro de vacaciones.
 * <p>
 * Define la operación que debe implementar el servicio de aplicación.
 * Recibe los datos encapsulados en {@link VacationRegisterCommand}.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public interface VacationRegisterInputPort {
	
	/**
	 * Registra una nueva solicitud de vacaciones a partir de los datos del comando.
	 * 
	 * @param command objeto que contiene los datos
	 * @return ID generado de la solicitud de vacaciones registrada
	 */
	public String vacationRegister(VacationRegisterCommand command);
}