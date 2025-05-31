package com.iem.tfm.application.port.input;

import com.iem.tfm.application.command.VacationStatusChangeCommand;

/**
 * Puerto de entrada para el caso de uso de cambio de estado de vacaciones.
 * <p>
 * Define la operación necesaria para cambia el estado de una solicitud de vacaciones,
 * en función del rol del usuario que realiza la acción.
 * Recibe los datos encapsulados en {@link VacationStatusChangeCommand}.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public interface VacationStatusInputPort {
	
	/**
	 * Cambia el estado de una solicitud de vacaciones.
	 * 
	 * @param command contiene los datos para el cambio de estado.
	 */
	public void statusChange(VacationStatusChangeCommand command);
}