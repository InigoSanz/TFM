package com.iem.tfm.domain.model;

import java.util.Date;

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
