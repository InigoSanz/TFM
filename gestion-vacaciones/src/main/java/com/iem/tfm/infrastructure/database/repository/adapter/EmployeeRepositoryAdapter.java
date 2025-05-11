package com.iem.tfm.infrastructure.database.repository.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iem.tfm.application.port.output.EmployeeRepositoryOutputPort;
import com.iem.tfm.domain.model.Employee;
import com.iem.tfm.infrastructure.database.entity.EmployeeEntity;
import com.iem.tfm.infrastructure.database.mapper.EmployeeEntityMapper;
import com.iem.tfm.infrastructure.database.repository.EmployeeRepository;

/**
 * Adaptador para conectar el puerto de salida con la infraestructura {@link EmployeeRepositoryOutputPort}.
 * 
 * Convierte la entidades en documentos de MongoDB mediante un mapper.
 * 
 * Delega las operaciones en el repositorio Mongo {@link EmployeeRepository}.
 * 
 * Punte entre la aplicación y la persistencia en BBDD.
 * 
 * @author Inigo
 * @version 1.0
 */
@Repository
public class EmployeeRepositoryAdapter implements EmployeeRepositoryOutputPort {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeEntityMapper employeeEntityMapper;

	@Override
	public String save(Employee employee) {
		EmployeeEntity entity = employeeEntityMapper.toEntity(employee);
		EmployeeEntity savedEmployee = employeeRepository.save(entity);
		
		String employeeId = savedEmployee.getId();
		
		return employeeId;
	}

	@Override
	public boolean existsByDni(String dni) {
		
		return employeeRepository.existsByDni(dni);
	}
}