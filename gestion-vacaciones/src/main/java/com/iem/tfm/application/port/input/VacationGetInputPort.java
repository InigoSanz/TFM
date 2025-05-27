package com.iem.tfm.application.port.input;

import java.util.List;

import com.iem.tfm.domain.model.Vacation;

/**
 * 
 */
public interface VacationGetInputPort {
	
	public List<Vacation> getAllVacation();
	
	public Vacation getVacation(String id);
	
	public List<Vacation> getEmployeeVacation(String id);
}
