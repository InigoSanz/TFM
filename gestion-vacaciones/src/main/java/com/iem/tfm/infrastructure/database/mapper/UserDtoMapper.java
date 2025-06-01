package com.iem.tfm.infrastructure.database.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.domain.model.User;
import com.iem.tfm.infrastructure.apirest.dto.response.UserResponseDto;

/**
 * Mapeador para convertir entre objetos del dominio {@link User} y DTOs de respuesta {@link UserResponseDto}.
 * <p>
 * Esta interfaz utiliza MapStruct para automatizar la conversión de objetos entre las capas de dominio e infraestructura,
 * facilitando el paso de datos al cliente sin exponer directamente la lógica interna del dominio.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {
	
	/**
	 * Convierte un objeto del dominio User en un DTO de respuesta UserResponseDto.
	 * 
	 * @param user objeto de dominio a convertir
	 * @return DTO equivalente para respuesta en la API
	 */
	public UserResponseDto toDto(User user);
	
	/**
	 * Convierte una lista de objetos del dominio User en una lista de DTOs de respuesta UserResponseDto.
	 * 
	 * @param users lista de objetos de dominio
	 * @return lista de DTOs de respuesta
	 */
	public List<UserResponseDto> toDtoList(List<User> users);
}