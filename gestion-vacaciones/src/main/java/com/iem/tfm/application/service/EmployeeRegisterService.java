package com.iem.tfm.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.command.EmployeeRegisterCommand;
import com.iem.tfm.application.port.input.EmployeeRegisterInputPort;
import com.iem.tfm.application.port.output.EmployeeRepositoryOutputPort;
import com.iem.tfm.domain.exception.EmployeeDomainException;
import com.iem.tfm.domain.model.Department;
import com.iem.tfm.domain.model.Employee;
import com.iem.tfm.domain.util.EmployeeRoleEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase de Servicio para implementar el caso de uso de registro de empleados.
 * 
 * Recibe los datos encapsulados de {@link EmployeeRegisterCommand}, construye la entidad {@code Employee} 
 * utilizando el patrón Builder para que luego el puerto de salida lo persista {@link EmployeeRepositoryOutputPort}.
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
	public String employeeRegister(EmployeeRegisterCommand command) {
		log.info("-> Inicio registro de empleado <-");
		
		// Comprobamos que el empleado ya esta en nuestro sistema
		if (employeeRepositoryOutput.existsByDni(command.getDni())) {
			throw new EmployeeDomainException("Ya existe un empleado con el DNI introducido.");
		}
		
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
				// Departamento ficticio para comprobar el registro
				.departments(List.of(new Department("liuywbf289735t923c7n", "Departamento ficticio"))) // TODO: cargar los departamentos reales por ID
				.role(roleEnum)
				.build();// TODO: cargar los departamentos reales por ID
		
		// Lo guardamos y obtenemos el id, es lo que devuelve el OutputPort
		String employeeId = employeeRepositoryOutput.save(employee);
		
		log.info("-> Empleado registrado exitosamente <-");
		
		return employeeId;
	}
}