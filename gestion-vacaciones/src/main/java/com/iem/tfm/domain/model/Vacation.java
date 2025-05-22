package com.iem.tfm.domain.model;

import java.util.Date;

import com.iem.tfm.domain.exception.VacationDomainException;
import com.iem.tfm.domain.util.VacationStatusEnum;

/**
 * Modelo de dominio para las vacaciones.
 * 
 * Encapsula las fechas de inicio y fin de las vacaciones y al
 * empleado al que pertenecen.
 * 
 * @author Inigo
 * @version 1.1
 */
public class Vacation {
	
	private String id;
	private Date startDate;
	private Date endDate;
	private Employee employee;
	private VacationStatusEnum status;
	
	public Vacation(String id, Date startDate, Date endDate, Employee employee, VacationStatusEnum status) {
		
		if (employee == null) {
			throw new VacationDomainException("Las vacaciones tienen que estar asociadas a un empleado.");
		}
		
		if (startDate == null || endDate == null) {
			throw new VacationDomainException("Las fechas para las vacaciones no pueden ser nulas.");
		}
		
		if (startDate.after(endDate)) {
			throw new VacationDomainException("El inicio de las vacaciones no puede ser posterior a la de fin.");
		}
		
		if (status == null) {
			throw new VacationDomainException("Las vacaciones deben contener un estado.");
		}
		
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employee = employee;
		this.status = status;
	}

	public String getId() {
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
	
	public VacationStatusEnum getStatus() {
		return status;
	}
}