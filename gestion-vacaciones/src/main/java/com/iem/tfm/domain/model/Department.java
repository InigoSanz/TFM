package com.iem.tfm.domain.model;

/**
 * Modelo de dominio para los departamentos.
 * 
 * Contiene la información básica de un departamento. 
 * 
 * Se utiliza para asociar empleados a uno o más departamentos.
 * 
 * @author Inigo
 * @version 1.0
 */
public class Department {
	
	private Long id;
	private String name;
	
	public Department(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}