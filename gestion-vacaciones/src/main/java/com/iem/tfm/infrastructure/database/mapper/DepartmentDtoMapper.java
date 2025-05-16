package com.iem.tfm.infrastructure.database.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.domain.model.Department;
import com.iem.tfm.infrastructure.apirest.dto.response.DepartmentResponseDto;

/**
 * 
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentDtoMapper {
	
	public DepartmentResponseDto toDto(Department department);

    public List<DepartmentResponseDto> toDtoList(List<Department> departments);
}