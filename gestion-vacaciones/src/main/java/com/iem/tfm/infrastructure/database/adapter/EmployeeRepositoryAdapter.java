package com.iem.tfm.infrastructure.database.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.iem.tfm.application.port.output.EmployeeRepositoryOutputPort;
import com.iem.tfm.domain.exception.EmployeeDomainException;
import com.iem.tfm.domain.model.Employee;
import com.iem.tfm.infrastructure.database.entity.EmployeeEntity;
import com.iem.tfm.infrastructure.database.mapper.EmployeeEntityMapper;
import com.iem.tfm.infrastructure.database.repository.EmployeeRepository;

/**
 * Adaptador que conecta la capa de aplicación con la infraestructura de
 * persistencia para empleados.
 * <p>
 * Implementa el puerto de salida {@link EmployeeRepositoryOutputPort} usando
 * Spring Data MongoDB. Convierte entre el modelo de dominio {@link Employee} y
 * la entidad de base de datos {@link EmployeeEntity}.
 * </p>
 * 
 * <p>
 * Actúa como puente entre el caso de uso y el repositorio real.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Component
public class EmployeeRepositoryAdapter implements EmployeeRepositoryOutputPort {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeEntityMapper employeeEntityMapper;

	/**
	 * Guarda un empleado en la BBDD.
	 * 
	 * @param employee objeto del dominio a guardar
	 * @return ID generado del nuevo empleado
	 */
	@Override
	public String save(Employee employee) {
		EmployeeEntity entity = employeeEntityMapper.toEntity(employee);
		EmployeeEntity savedEmployee = employeeRepository.save(entity);

		return savedEmployee.getId();
	}

	/**
	 * Verifica si ya existe un empleado con el DNI dado.
	 *
	 * @param dni documento de identidad
	 * @return {@code true} si el empleado existe, {@code false} si no
	 */
	@Override
	public boolean existsByDni(String dni) {

		return employeeRepository.existsByDni(dni);
	}

	/**
	 * Recupera todos los empleados del sistema.
	 *
	 * @return lista de empleados con sus departamentos completos
	 */
	@Override
	public List<Employee> findAll() {
		List<EmployeeEntity> entities = employeeRepository.findAll();

		return employeeEntityMapper.toDomainList(entities);
	}

	/**
	 * Recupera un empleado por su ID, incluyendo sus departamentos.
	 *
	 * @param id identificador del empleado
	 * @return empleado del dominio reconstruido
	 * @throws EmployeeDomainException si el empleado no existe o los departamentos
	 *                                 no coinciden
	 */
	@Override
	public Employee findEmployeeById(String id) {
		Optional<EmployeeEntity> entityOptional = employeeRepository.findById(id);

		if (!entityOptional.isPresent()) {
			throw new EmployeeDomainException("Empleado no encontrado con id: " + id);
		}

		return employeeEntityMapper.toDomain(entityOptional.get());
	}

	/**
	 * Verifica si existe un empleado por su ID.
	 * 
	 * @param employeeId ID del empleado
	 * @return {@code true} si el empleado existe, {@code false} en caso contrario
	 */
	@Override
	public boolean existsById(String employeeId) {

		return employeeRepository.existsById(employeeId);
	}

	/**
	 * Recupera todos los empleados pertenecientes a uno o más departamentos.
	 * 
	 * @param departmentIds lista de IDs de departamentos
	 * @return lista de empleados asociados a esos departamentos
	 */
	@Override
	public List<Employee> findEmployeesByDepartmentId(List<String> departmentIds) {
		List<EmployeeEntity> entityOptional = employeeRepository.findByDepartmentIdsIn(departmentIds);

		return employeeEntityMapper.toDomainList(entityOptional);
	}

	/**
	 * Recupera una página de empleados pertenecientes a un departamento.
	 * 
	 * @param departmentId ID del departamento
	 * @param page         número de página
	 * @param size         tamaño de página
	 * @return página de empleados del dominio
	 */
	@Override
	public Page<Employee> findPaginatedByDepartment(String departmentId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<EmployeeEntity> entityPage = employeeRepository.findByDepartmentIdsContaining(departmentId, pageable);

		List<Employee> employeeList = employeeEntityMapper.toDomainList(entityPage.getContent());

		return new PageImpl<>(employeeList, pageable, entityPage.getTotalElements());
	}
}