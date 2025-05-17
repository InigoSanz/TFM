package com.iem.tfm.application.port.input;

import java.util.List;

import com.iem.tfm.domain.model.Department;

/**
 * Puerto de entrada para la consulta de departamentos.
 * <p>
 * Define las operaciones disponibles para obtener departamentos desde
 * adaptadores externos, como controladores REST.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public interface DepartmentGetInputPort {

	/**
	 * Devuelve todos los departamentos registrados en el sistema.
	 *
	 * @return lista de departamentos
	 */
	public List<Department> getAllDepartment();

	/**
	 * Devuelve un departamento a partir de su ID.
	 *
	 * @param id identificador del departamento
	 * @return departamento correspondiente al ID
	 */
	public Department getDepartment(String id);
}