package com.iem.tfm.application.port.output;

import java.util.List;

import com.iem.tfm.domain.model.Department;

/**
 * Puerto de salida para las operaciones de persistencia y consulta de
 * {@link Department}.
 * <p>
 * Define las acciones requeridas por la capa de aplicación para trabajar con
 * los departamentos. Será implementado por un adaptador en la capa de
 * infraestructura.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public interface DepartmentRepositoryOutputPort {

	/**
	 * Busca todos los departamentos por sus IDs.
	 *
	 * @param ids lista de identificadores
	 * @return lista de departamentos encontrados
	 */
	public List<Department> findAllById(List<String> ids);

	/**
	 * Devuelve todos los departamentos registrados.
	 *
	 * @return lista completa de departamentos
	 */
	public List<Department> findAll();

	/**
	 * Busca un departamento por su ID.
	 *
	 * @param id identificador del departamento
	 * @return departamento correspondiente
	 */
	public Department findDepartmentById(String id);
}