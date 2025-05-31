package com.iem.tfm.infrastructure.database.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.domain.model.User;
import com.iem.tfm.infrastructure.database.entity.UserEntity;

/**
 * 
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
	
	public UserEntity toEntity(User user);
	
	public User toDomain(UserEntity entity);
}