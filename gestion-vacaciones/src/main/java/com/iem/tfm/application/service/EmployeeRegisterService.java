package com.iem.tfm.application.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.command.EmployeeRegisterCommand;
import com.iem.tfm.application.port.input.EmployeeRegisterInputPort;
import com.iem.tfm.application.port.output.EmployeeRepositoryOutputPort;
import com.iem.tfm.domain.model.Employee;
import com.iem.tfm.domain.util.EmployeeRoleEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase de Servicio para implementar el caso de uso de registro de empleados.
 * 
 * Recibe los datos encapsulados de {@link EmployeeRegisterCommand}, construye la entidad {@code Employee} 
 * utilizando el patrón builder para que luego el puerto de salida lo persista {@link EmployeeRepositoryOutputPort}.
 * 
 * De momento creamos un arraylist vacio para que compile, ya que no tenemos los departamentos.
 * 
 * @author Inigo
 * @version 1.0
 */
@Service
@Slf4j
public class EmployeeRegisterService implements EmployeeRegisterInputPort {
	
	@Autowired
	EmployeeRepositoryOutputPort employeeRepositoryOutput;
	
	@Override
	public void employeeRegister(EmployeeRegisterCommand command) {
		log.info("-> Inicio registro de empleado <-");
		
		// Aquí habría que validar que el empleado ya esta dado de alta o que existe
		
		
		// Necesitamos obtener el valor del Enum, ya que en el command tenemos un String		 
		EmployeeRoleEnum roleEnum = EmployeeRoleEnum.valueOf(command.getRole().toUpperCase());
		
		// Creamos el empleado
		Employee employee = new Employee.Builder()
				.name(command.getName())
				.surname(command.getSurname())
				.dni(command.getDni())
				.age(command.getAge())
				.email(command.getEmail())
				.startDate(command.getStartDate())
				.departments(new ArrayList<>()) // Lista vacía hasta tener los departamentos
				.role(roleEnum)
				.build();
		
		// Lo guardamos
		employeeRepositoryOutput.save(employee);
		
		log.info("-> Empleado registrado exitosamente <-");
	}
}