package com.iem.tfm.infrastructure.database.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.domain.model.Department;
import com.iem.tfm.infrastructure.apirest.dto.response.DepartmentResponseDto;

/**
 * Mapper de infraestructura que convierte entre objetos de dominio
 * {@link Department} y objetos de respuesta {@link DepartmentResponseDto}
 * usados en la API REST.
 * 
 * <p>
 * Se implementa automáticamente usando MapStruct.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentDtoMapper {

	/**
	 * Convierte un objeto del dominio a su representación como DTO.
	 * 
	 * @param department departamento del dominio
	 * @return DTO para la capa REST
	 */
	public DepartmentResponseDto toDto(Department department);

	/**
	 * Convierte una lista de departamentos del dominio a DTOs de respuesta.
	 * 
	 * @param departments lista del dominio
	 * @return lista de DTOs para enviar al cliente
	 */
	public List<DepartmentResponseDto> toDtoList(List<Department> departments);
}