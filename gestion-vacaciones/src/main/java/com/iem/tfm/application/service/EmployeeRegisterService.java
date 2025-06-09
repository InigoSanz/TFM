package com.iem.tfm.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.port.input.EmployeeRegisterInputPort;
import com.iem.tfm.application.port.output.DepartmentRepositoryOutputPort;
import com.iem.tfm.application.port.output.EmployeeRepositoryOutputPort;
import com.iem.tfm.application.port.output.UserRepositoryOutputPort;
import com.iem.tfm.domain.command.EmployeeRegisterCommand;
import com.iem.tfm.domain.exception.EmployeeDomainException;
import com.iem.tfm.domain.model.Employee;
import com.iem.tfm.domain.model.EmployeeRoleEnum;
import com.iem.tfm.domain.model.User;
import com.iem.tfm.domain.model.UserRoleEnum;
import com.iem.tfm.domain.util.PasswordGenerator;

import lombok.extern.slf4j.Slf4j;

/**
 * Servicio de aplicación que implementa el caso de uso de registro de empleados.
 * <p>
 * Recibe un {@link EmployeeRegisterCommand}, valida los datos y construye una
 * entidad {@link Employee}, que luego se persiste a tavés del {@link EmployeeRepositoryOutputPort}.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Service
@Slf4j
public class EmployeeRegisterService implements EmployeeRegisterInputPort {
	
	@Autowired
	EmployeeRepositoryOutputPort employeeRepositoryOutput;
	
	@Autowired
	DepartmentRepositoryOutputPort departmentRepositoryOutput;
	
	@Autowired
	UserRepositoryOutputPort userRepositoryOutput;
	
	/**
	 * Registra un nuevo empleado en el sistema.
	 * <p>
	 * Valida el DNI, convierte el rol desde texto a Enum, recupera los departamentos,
	 * construye el objeto del dominio y lo guarda.
	 * </p>
	 * 
	 * @param command Command con los datos del nuevo empleado
	 * @return ID generado del empleado registrado
	 */
	@Override
	public String employeeRegister(EmployeeRegisterCommand command) {
		log.debug("-> Inicio registro de empleado <-");
		
		if (employeeRepositoryOutput.existsByDni(command.getDni())) {
			throw new EmployeeDomainException("Ya existe un empleado con el DNI introducido.");
		}
				 
		EmployeeRoleEnum roleEnum = EmployeeRoleEnum.valueOf(command.getRole().toUpperCase());
		
		List<String> departmentIdList = command.getDepartmentIds();
		
		// Validamos que todos los departamentos solicitados existen
		if (departmentIdList == null || departmentIdList.isEmpty()) {
			throw new EmployeeDomainException("-> El empleado debe tener al menos un ID de departamento <-");
		}
		
		Employee employee = Employee.builder()
				.name(command.getName())
				.surname(command.getSurname())
				.dni(command.getDni())
				.age(command.getAge())
				.email(command.getEmail())
				.startDate(command.getStartDate())
				.departmentIds(departmentIdList)
				.role(roleEnum)
				.build();
		
		String employeeId = employeeRepositoryOutput.save(employee);
		
		log.debug("-> Empleado registrado exitosamente <-");
		
		// Creamos el usuario del empleado que se da de alta
		String password = PasswordGenerator.generatePassword(10);
		User user = new User(null, command.getEmail(), password, UserRoleEnum.USER, true, employeeId);
		
		userRepositoryOutput.save(user);
		
		log.debug("-> Usuario creado para el empleado <-");
		log.debug("Username: {}", user.getUsername());
		log.debug("Password: {}", password);
		
		return employeeId;
	}
}