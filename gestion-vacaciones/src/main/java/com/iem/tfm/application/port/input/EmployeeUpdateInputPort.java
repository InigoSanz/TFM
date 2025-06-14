package com.iem.tfm.application.port.input;

import com.iem.tfm.domain.command.EmployeeUpdateCommand;

/**
 * 
 */
public interface EmployeeUpdateInputPort {
	
	/**
	 * 
	 * @param command
	 */
	public void updateEmployee(EmployeeUpdateCommand command);
}