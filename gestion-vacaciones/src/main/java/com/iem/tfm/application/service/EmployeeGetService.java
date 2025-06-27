package com.iem.tfm.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.port.input.EmployeeGetInputPort;
import com.iem.tfm.application.port.output.EmployeeRepositoryOutputPort;
import com.iem.tfm.domain.model.Employee;

import lombok.extern.slf4j.Slf4j;

/**
 * Servicio de aplicación para consultar empleados dados de alta.
 * <p>
 * Implementa el caso de uso definido en {@link EmployeeGetInputPort} y accede a
 * los datos mediante {@link EmployeeRepositoryOutputPort}.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Service
@Slf4j
public class EmployeeGetService implements EmployeeGetInputPort {

	@Autowired
	EmployeeRepositoryOutputPort employeeRepositoryOutput;

	/**
	 * Obtiene todos los empleados del sistema.
	 * 
	 * @return lista de empleados
	 */
	@Override
	public List<Employee> getAllEmployees() {
		log.debug("-> Obteniendo todos los empleados <-");

		return employeeRepositoryOutput.findAll();
	}

	/**
	 * Obtiene un empleado por su ID.
	 * 
	 * @param id identificador del empleado
	 * @return empleado correspondiente al ID
	 */
	@Override
	public Employee getEmployee(String id) {
		log.debug("-> Obteniendo un empleado <-");

		return employeeRepositoryOutput.findEmployeeById(id);
	}

	/**
	 * Obtiene los empleados que pertenecen a uno o varios departamentos.
	 * 
	 * @param departmentIds lista de IDs de departamentos
	 * @return lista de empleados asociados a los departamentos
	 */
	@Override
	public List<Employee> getEmployeesByDepartment(List<String> departmentIds) {
		log.debug("-> Obteniendo los empleados del departamento: {}", departmentIds);

		return employeeRepositoryOutput.findEmployeesByDepartmentId(departmentIds);
	}

	/**
	 * Obtiene los empleados de un departamento específico de forma paginada.
	 * 
	 * @param departmentId ID del departamento
	 * @param page         número de página (empezando desde 0)
	 * @param size         número de elementos por página
	 * @return página de empleados del departamento indicado
	 */
	@Override
	public Page<Employee> getPaginatedEmployeesByDepartment(String departmentId, int page, int size) {
		log.debug("-> Obteniendo empleados del departamento [{}] paginados (página {}, tamaño {}) <-", departmentId,
				page, size);
		return employeeRepositoryOutput.findPaginatedByDepartment(departmentId, page, size);
	}
}