package com.iem.tfm.application.port.input;

import com.iem.tfm.application.command.VacationStatusChangeCommand;

/**
 * 
 */
public interface VacationStatusInputPort {
	
	public void statusChange(VacationStatusChangeCommand command);
}