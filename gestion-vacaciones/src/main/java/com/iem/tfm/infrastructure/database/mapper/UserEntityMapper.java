package com.iem.tfm.infrastructure.database.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.domain.model.User;
import com.iem.tfm.infrastructure.database.entity.UserEntity;

/**
 * Mapeador para la conversión entre la entidad de base de datos
 * {@link UserEntity} y el modelo de dominio {@link User}.
 * <p>
 * Utiliza MapStruct para facilitar la transformación automática entre los datos
 * persistidos en MongoDB y los objetos del dominio utilizados por la lógica de
 * negocio.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

	/**
	 * Convierte un objeto del dominio User en su equivalente entidad UserEntity.
	 * 
	 * @param user objeto del dominio a convertir
	 * @return entidad lista para persistencia
	 */
	public UserEntity toEntity(User user);

	/**
	 * Convierte una entidad UserEntity recuperada de la BBDD en el objeto de
	 * dominio User.
	 * 
	 * @param entity entidad de base de datos
	 * @return objeto del dominio
	 */
	public User toDomain(UserEntity entity);

	/**
	 * Convierte una lista de entidades UserEntity en una lista de objetos de
	 * dominio User.
	 * 
	 * @param entities lista de entidades recuperadas de la BBDD
	 * @return lista de objetos del dominio
	 */
	public List<User> toDomainList(List<UserEntity> entities);
}