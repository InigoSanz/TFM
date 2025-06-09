package com.iem.tfm.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Modelo de dominio que representa un departamento dentro de la empresa.
 * <p>
 * Contiene la información esencial del departamento y se utiliza para asociar empleados
 * a uno o más departamentos.
 * </p>
 * 
 * @author Inigo
 * @version 1.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Department {
	
	private String id;
	private String name;
}