package com.iem.tfm.infrastructure.database.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import com.iem.tfm.domain.model.Department;
import com.iem.tfm.domain.model.Vacation;
import com.iem.tfm.infrastructure.database.entity.VacationEntity;

/**
 * 
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class VacationEntityMapper {
	
	@Autowired
	EmployeeEntityMapper employeeEntityMapper;
	
	public abstract  VacationEntity toEntity(Vacation vacation);
	
	public Vacation toDomain(VacationEntity entity, List<Department> departments) {
		return new Vacation(
				entity.getId(),
				entity.getStartDate(),
				entity.getEndDate(),
				employeeEntityMapper.toDomain(entity.getEmployee(), departments),
				entity.getStatus()
			);			
	}
}