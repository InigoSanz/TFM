package com.iem.tfm.domain.model;

import java.util.Date;

import com.iem.tfm.domain.exception.VacationDomainException;

/**
 * Modelo de dominio para las vacaciones.
 * 
 * Encapsula las fechas de inicio y fin de las vacaciones y al
 * empleado al que pertenecen.
 * 
 * @author Inigo
 * @version 1.0
 */
public class Vacation {
	
	private Long id;
	private Date startDate;
	private Date endDate;
	private Employee employee;
	
	public Vacation(Long id, Date startDate, Date endDate, Employee employee) {
		
		if (employee == null) {
			throw new VacationDomainException("Las vacaciones tienen que estar asociadas a un empleado.");
		}
		
		if (startDate == null || endDate == null) {
			throw new VacationDomainException("Las fechas para las vacaciones no pueden ser nulas.");
		}
		
		if (startDate.after(endDate)) {
			throw new VacationDomainException("El inicio de las vacaciones no puede ser posterior a la de fin.");
		}
		
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Employee getEmployee() {
		return employee;
	}
}