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
	 * Verifica si existe un empleado con el ID especificado.
	 * 
	 * @param employeeId identificador del empleado
	 * @return {@code true} si existe un empleado con ese ID, {@code false} en caso
	 *         contrario
	 */
	public boolean existsById(String employeeId);

	/**
	 * Busca empleados que pertenezcan a una o más IDs de departamento.
	 * 
	 * @param departmentIds lista de identificadores de departamentos
	 * @return lista de empleados asociados a los departamentos
	 */
	public List<Employee> findEmployeesByDepartmentId(List<String> departmentIds);

	/**
	 * Devuelve una página de empleados pertenecientes a un departamento.
	 * 
	 * @param departmentId ID del departamento
	 * @param page         número de página (empezando desde 0)
	 * @param size         cantidad de elementos por página
	 * @return página con empleados del departamento indicado
	 */
	public Page<Employee> findPaginatedByDepartment(String departmentId, int page, int size);
}