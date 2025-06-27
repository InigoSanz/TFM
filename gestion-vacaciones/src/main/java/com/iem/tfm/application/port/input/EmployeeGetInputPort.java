package com.iem.tfm.application.port.input;

import java.util.List;

import org.springframework.data.domain.Page;

import com.iem.tfm.domain.model.Employee;

/**
 * Puerto de entrada para la obtención de empleados.
 * <p>
 * Define las operaciones disponivles para obtener empleados desde adaptadores
 * externos, como controladores REST.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public interface EmployeeGetInputPort {

	/**
	 * Devuelve una lista con todos los empleados dados de alta.
	 * 
	 * @return lista de empleados
	 */
	public List<Employee> getAllEmployees();

	/**
	 * Obtiene un empleado por su ID.
	 * 
	 * @param id identificador del empleado
	 * @return empleado correspondiente al ID
	 */
	public Employee getEmployee(String id);

	/**
	 * Devuelve una lista de empleados que pertenecen a uno o varios departamentos.
	 * 
	 * @param departmentIds lista de identificadores de departamentos
	 * @return lista de empleados asociados a los departamentos
	 */
	public List<Employee> getEmployeesByDepartment(List<String> departmentIds);

	/**
	 * Devuelve una página de empleados que pertenecen a un departamento específico.
	 * 
	 * @param departmentId identificador del departamento
	 * @param page         número de página (empezando desde 0)
	 * @param size         cantidad de elementos por página
	 * @return página con empleados del departamento indicado
	 */
	public Page<Employee> getPaginatedEmployeesByDepartment(String departmentId, int page, int size);
}