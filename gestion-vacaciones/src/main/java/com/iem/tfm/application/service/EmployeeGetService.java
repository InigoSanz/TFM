package com.iem.tfm.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.port.input.EmployeeGetInputPort;
import com.iem.tfm.application.port.output.EmployeeRepositoryOutputPort;
import com.iem.tfm.domain.model.Employee;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase de Servicio para la obtención de todos los empleados.
 * 
 * Implementa el puerto de entrada {@link EmployeeGetInputPort} y el puerto de salida
 * {@link EmployeeRepositoryOutputPort}.
 * 
 * @author Inigo
 * @version 1.0
 */
@Service
@Slf4j
public class EmployeeGetService implements EmployeeGetInputPort {
	
	@Autowired
	EmployeeRepositoryOutputPort employeeRepositoryOutput;
	
	@Override
	public List<Employee> getAllEmployees() {		
		log.info("-> Obteniendo todos los empleados <-");
		
		return employeeRepositoryOutput.findAll();
	}
}