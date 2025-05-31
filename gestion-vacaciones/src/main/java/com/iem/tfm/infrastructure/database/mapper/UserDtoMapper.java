package com.iem.tfm.infrastructure.database.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.domain.model.User;
import com.iem.tfm.infrastructure.apirest.dto.response.UserResponseDto;

/**
 * 
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {
	
	public UserResponseDto toDto(User user);
	
	public List<UserResponseDto> toDtoList(List<User> users);
}