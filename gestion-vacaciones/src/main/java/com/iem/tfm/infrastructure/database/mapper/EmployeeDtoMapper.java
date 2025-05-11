package com.iem.tfm.infrastructure.database.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.application.command.EmployeeRegisterCommand;
import com.iem.tfm.infrastructure.apirest.dto.request.EmployeeRequestDto;

/**
 * Mapper de la capa de infraestructura para convertir DTO a Command.
 * 
 * Transforma un {@link EmployeeRequestDto} a un {@link EmployeeRegisterCommand}.
 * 
 * Se implementa utilizando MapStruct.
 * 
 * @author Inigo
 * @version 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeDtoMapper {
	
	public EmployeeRegisterCommand fromDtoToCommand(EmployeeRequestDto dto);
}