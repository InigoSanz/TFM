package com.iem.tfm.infrastructure.database.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.domain.command.EmployeeRegisterCommand;
import com.iem.tfm.domain.command.EmployeeUpdateCommand;
import com.iem.tfm.domain.model.Employee;
import com.iem.tfm.infrastructure.apirest.dto.request.EmployeeRequestDto;
import com.iem.tfm.infrastructure.apirest.dto.request.EmployeeUpdateRequestDto;
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
public interface EmployeeDtoMapper {

	/**
	 * Convierte un DTO recibido desde la API en un command de aplicación.
	 * 
	 * @param dto DTO de entrada
	 * @return command listo para usarse en la capa de aplicación
	 */
	public EmployeeRegisterCommand fromDtoToCommand(EmployeeRequestDto dto);

	/**
	 * Convierte una lista de empleados del dominio a una lista de DTOs de
	 * respuesta.
	 * 
	 * @param employees lista de objetos de dominio
	 * @return lista de DTOs para responder al cliente
	 */
	public List<EmployeeResponseDto> fromDomainToDtoList(List<Employee> employees);

	/**
	 * Convierte un {@link Employee} del dominio a un {@link EmployeeResponseDto},
	 * incluyendo el mapeo de departamentos.
	 * 
	 * @param employee objeto del dominio
	 * @return DTO de respuesta listo para devolver al cliente
	 */
	public default EmployeeResponseDto fromDomainToDto(Employee employee) {
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

		dto.setDepartmentIds(employee.getDepartmentIds());

		return dto;
	}

	/**
	 * Convierte un {@link EmployeeUpdateRequestDto} junto con un ID en un
	 * {@link EmployeeUpdateCommand}, que puede ser utilizado en la capa de
	 * aplicación para actualizar los datos de un empleado.
	 *
	 * @param id  identificador del empleado a actualizar
	 * @param dto DTO con los datos a modificar
	 * @return comando de actualización para ser procesado por la lógica de negocio
	 */
	public default EmployeeUpdateCommand fromDtoToUpdateCommand(String id, EmployeeUpdateRequestDto dto) {
		return new EmployeeUpdateCommand(id, dto.getAge(), dto.getEmail(), dto.getStartDate(), dto.getEndDate(),
				dto.getDepartmentIds(), dto.getRole());
	}
}