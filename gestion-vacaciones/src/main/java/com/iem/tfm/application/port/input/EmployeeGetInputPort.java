package com.iem.tfm.application.port.input;

import java.util.List;

import com.iem.tfm.domain.model.Employee;

/**
 * Puerto de entrada para la obtención de empleados.
 * 
 * @author Inigo
 * @version 1.0
 */
public interface EmployeeGetInputPort {
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployee(String id);	
}