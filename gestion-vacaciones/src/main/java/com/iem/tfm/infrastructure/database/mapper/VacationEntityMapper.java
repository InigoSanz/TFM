package com.iem.tfm.infrastructure.database.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.domain.model.Vacation;
import com.iem.tfm.infrastructure.database.entity.VacationEntity;

/**
 * 
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VacationEntityMapper {
	
	public VacationEntity toEntity(Vacation vacation);
	
	public Vacation toDomain(VacationEntity entity);
}