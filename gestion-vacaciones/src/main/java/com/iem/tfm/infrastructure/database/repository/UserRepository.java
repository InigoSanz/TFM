package com.iem.tfm.infrastructure.database.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.iem.tfm.infrastructure.database.entity.UserEntity;

/**
 * Repositorio de acceso a datos para la entidad {@link UserEntity}.
 * <p>
 * Esta interfaz extiende de {@link MongoRepository} y proporciona métodos
 * específicos para realizar consultas sobre la colección "USERS" en MongoDB.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Repository
@EnableMongoRepositories
public interface UserRepository extends MongoRepository<UserEntity, String> {

	/**
	 * Busca un usuario por su nombre de usuario.
	 * 
	 * @param username nombre de usuario
	 * @return un Optional que puede contener el UserEntity correspondiente
	 */
	public Optional<UserEntity> findByUsername(String username);
	
	/**
	 * Comprueba si existe un usuario con el nombre de usuario dado.
	 * 
	 * @param username nombre de usuario a verificar
	 * @return true si el usuario existe, false si no
	 */
	public boolean existsByUsername(String username);
}