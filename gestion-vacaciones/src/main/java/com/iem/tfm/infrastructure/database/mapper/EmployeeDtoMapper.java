package com.iem.tfm.infrastructure.database.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import com.iem.tfm.application.command.EmployeeRegisterCommand;
import com.iem.tfm.domain.model.Employee;
import com.iem.tfm.infrastructure.apirest.dto.request.EmployeeRequestDto;
import com.iem.tfm.infrastructure.apirest.dto.response.DepartmentResponseDto;
import com.iem.tfm.infrastructure.apirest.dto.response.EmployeeResponseDto;

/**
 * Mapper de infraestructura que convierte entre DTOs y modelos de aplicación o
 * dominio.
 * <p>
 * Transforma un {@link EmployeeRequestDto} en un
 * {@link EmployeeRegisterCommand}, y un {@link Employee} en un
 * {@link EmployeeResponseDto}.
 * </p>
 * 
 * <p>
 * Se implementa parcialmente con MapStruct, y los departamentos de forma
 * manual.
 * </p>
 * <p>
 * Es una clase abstracta para permitir lógica personalizada, principalmente
 * porque inyectamos otro mapper.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class EmployeeDtoMapper {

	@Autowired
	protected DepartmentDtoMapper departmentDtoMapper;

	/**
	 * Convierte un DTO recibido desde la API en un command de aplicación.
	 * 
	 * @param dto DTO de entrada
	 * @return command listo para usarse en la capa de aplicación
	 */
	public abstract EmployeeRegisterCommand fromDtoToCommand(EmployeeRequestDto dto);

	/**
	 * Convierte una lista de empleados del dominio a una lista de DTOs de
	 * respuesta.
	 * 
	 * @param employees lista de objetos de dominio
	 * @return lista de DTOs para responder al cliente
	 */
	public abstract List<EmployeeResponseDto> fromDomainToDtoList(List<Employee> employees);

	/**
	 * Convierte un {@link Employee} del dominio a un {@link EmployeeResponseDto},
	 * incluyendo el mapeo de departamentos.
	 * 
	 * @param employee objeto del dominio
	 * @return DTO de respuesta listo para devolver al cliente
	 */
	public EmployeeResponseDto fromDomainToDto(Employee employee) {
		EmployeeResponseDto dto = new EmployeeResponseDto();

		dto.setId(employee.getId());
		dto.setName(employee.getName());
		dto.setSurname(employee.getSurname());
		dto.setDni(employee.getDni());
		dto.setAge(employee.getAge());
		dto.setEmail(employee.getEmail());
		dto.setStartDate(employee.getStartDate());
		dto.setEndDate(employee.getEndDate());
		dto.setRole(employee.getRole());
		
		// Se usa el mapper de departamentos para convertir cada uno a su DTO
		List<DepartmentResponseDto> departmentDtos = departmentDtoMapper.toDtoList(employee.getDepartments());
		dto.setDepartments(departmentDtos);

		return dto;
	}
}