package com.iem.tfm.application.port.output;

import java.util.Date;
import java.util.List;

import com.iem.tfm.domain.model.Vacation;

/**
 * 
 */
public interface VacationRepositoryOutputPort {
	
	public String save(Vacation vacation);
	
	public List<Vacation> findByEmployeeIdAndDateOverlap(String employeeId, Date startDate, Date endDate);
}