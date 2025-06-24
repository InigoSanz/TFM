package com.iem.tfm.application.port.output;

import java.util.List;

import org.springframework.data.domain.Page;

import com.iem.tfm.domain.model.Employee;

/**
 * Puerto de salida para operaciones de persistencia y consulta de
 * {@link Employee}.
 * <p>
 * Define las acciones que la capa de aplicación necesita realizar sobre el
 * repositorio de empleados. Será implementado por un adaptador en la capa de
 * infraestructura.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public interface EmployeeRepositoryOutputPort {

	/**
	 * Guarda un nuevo empleado en el sistema.
	 * 
	 * @param employee empleado a guardar
	 * @return ID generado del empleado
	 */
	public String save(Employee employee);

	/**
	 * Verifica si existe un empleado con el DNI especificado.
	 * 
	 * @param dni documento de identidad
	 * @return {@code true} si el empleado existe, {@code false} si no
	 */
	public boolean existsByDni(String dni);

	/**
	 * Devuelve todos los empleados dados de alta.
	 * 
	 * @return lista de empleados
	 */
	public List<Employee> findAll();

	/**
	 * Busca un empleado por su identificador único.
	 * 
	 * @param id ID del empleado
	 * @return empleado correspondiente al ID
	 */
	public Employee findEmployeeById(String id);

	/**
	 * 
	 * @param employeeId
	 * @return
	 */
	public boolean existsById(String employeeId);
	
	/**
	 * 
	 * @param departmentId
	 * @return
	 */
	public List<Employee> findEmployeesByDepartmentId(List<String> departmentIds);
	
	/**
	 * 
	 * @param departmentId
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Employee> findPaginatedByDepartment(String departmentId, int page, int size);
}