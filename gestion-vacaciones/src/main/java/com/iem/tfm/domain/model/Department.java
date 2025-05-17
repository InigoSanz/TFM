package com.iem.tfm.domain.model;

import com.iem.tfm.domain.exception.DepartmentDomainException;

/**
 * Modelo de dominio que representa un departamento dentro de la empresa.
 * <p>
 * Contiene la información esencial del departamento y se utiliza para asociar empleados
 * a uno o más departamentos.
 * </p>
 * 
 * <p>
 * Aplica una validación de negocio para asegurar que el nombre no sea nulo.
 * </p>
 * 
 * @author Inigo
 * @version 1.1
 */
public class Department {
	
	private String id;
	private String name;
	
	/**
	 * Constructor del modelo de dominio.
	 * Lanza una excepción si el nombre es nulo.
	 *
	 * @param id identificador del departamento
	 * @param name nombre del departamento
	 */
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