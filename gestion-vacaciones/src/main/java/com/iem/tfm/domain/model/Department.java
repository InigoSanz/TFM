package com.iem.tfm.domain.model;

import com.iem.tfm.domain.exception.DepartmentDomainException;

/**
 * Modelo de dominio para los departamentos.
 * 
 * Contiene la información básica de un departamento. 
 * 
 * Se utiliza para asociar empleados a uno o más departamentos.
 * 
 * @author Inigo
 * @version 1.1
 */
public class Department {
	
	private String id;
	private String name;
	
	public Department(String id, String name) {
		
		if (name == null) {
			throw new DepartmentDomainException("El nombre del departamento no puede ser nulo.");
		}
		
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}