package com.iem.tfm.application.port.input;

import java.util.List;

import com.iem.tfm.domain.model.Employee;

/**
 * Puerto de entrada para la obtención de empleados.
 * <p>
 * Define las operaciones disponivles para obtener empleados desde
 * adaptadores externos, como controladores REST.
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
}