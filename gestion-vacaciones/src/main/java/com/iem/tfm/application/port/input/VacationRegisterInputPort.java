package com.iem.tfm.application.port.input;

import com.iem.tfm.application.command.VacationRegisterCommand;

/**
 * 
 */
public interface VacationRegisterInputPort {
	
	public String vacationRegister(VacationRegisterCommand command);
}