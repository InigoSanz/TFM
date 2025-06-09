package com.iem.tfm.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}