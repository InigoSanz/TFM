package com.iem.tfm.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.port.input.EmployeeUpdateInputPort;
import com.iem.tfm.application.port.output.EmployeeRepositoryOutputPort;
import com.iem.tfm.domain.command.EmployeeUpdateCommand;
import com.iem.tfm.domain.model.Employee;
import com.iem.tfm.domain.model.EmployeeRoleEnum;

/**
 * 
 */
@Service
public class EmployeeUpdateService implements EmployeeUpdateInputPort {
	
	@Autowired
	EmployeeRepositoryOutputPort employeeRepositoryOutput;
	
	/**
	 * 
	 */
	@Override
	public void updateEmployee(EmployeeUpdateCommand command) {
		
		// Habría que comprobar que existe aquí?
		Employee workingEmployee = employeeRepositoryOutput.findEmployeeById(command.getId());
		
		Employee updatedEmployee = Employee.builder()
				.id(workingEmployee.getId())
				.name(workingEmployee.getName())
				.surname(workingEmployee.getSurname())
				.dni(workingEmployee.getDni())
				.age(command.getAge())
				.email(command.getEmail())
				.startDate(command.getStartDate())
				.endDate(command.getEndDate())
				.departmentIds(command.getDepartmentIds())
				.role(EmployeeRoleEnum.valueOf(command.getRole()))
				.build();
		
		employeeRepositoryOutput.save(updatedEmployee);	
	}
}