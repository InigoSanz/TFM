package com.iem.tfm.application.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.port.input.EmployeeDeactivateInputPort;
import com.iem.tfm.application.port.output.EmployeeRepositoryOutputPort;
import com.iem.tfm.domain.exception.EmployeeDomainException;
import com.iem.tfm.domain.model.Employee;

import lombok.extern.slf4j.Slf4j;

/**
 * Este servicio lo que hara será dar de baja un empleado con su endDate.
 */
@Service
@Slf4j
public class EmployeeDeactivateService implements EmployeeDeactivateInputPort {

	@Autowired
	EmployeeRepositoryOutputPort employeeRepositoryOutput;

	@Override
	public void deactivateEmployee(String employeeId) {
		
		Employee employee = employeeRepositoryOutput.findEmployeeById(employeeId);
		
		if (employee == null) {
			throw new EmployeeDomainException("Empleado no encontrado con ID: " + employeeId);
		}
		
		if (employee.getEndDate() != null) {
			throw new EmployeeDomainException("El empleaado ya está dado de baja.");
		}
		
		Employee updatedEmployee = Employee.builder()
				.id(employee.getId())
				.name(employee.getName())
				.surname(employee.getSurname())
				.dni(employee.getDni())
				.age(employee.getAge())
				.email(employee.getEmail())
				.startDate(employee.getStartDate())
				.endDate(new Date()) // fecha de baja, cuando se llame al servicio (de momento lo dejamos asi)
				.departmentIds(employee.getDepartmentIds())
				.role(employee.getRole())
				.build();
		
		employeeRepositoryOutput.save(updatedEmployee);
	}
}