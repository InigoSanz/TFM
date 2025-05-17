package com.iem.tfm.infrastructure.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.iem.tfm.infrastructure.database.entity.DepartmentEntity;

/**
 * Repositorio de MongoDB para la entidad {@link DepartmentEntity}.
 * 
 * <p>
 * Extiende {@link MongoRepository} para proporcionar métodos CRUD y consultas
 * personalizadas.
 * </p>
 * 
 * <p>
 * Se utiliza desde los adaptadores para acceder a la base de datos.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Repository
@EnableMongoRepositories
public interface DepartmentRepository extends MongoRepository<DepartmentEntity, String> {

	/**
	 * Comprueba si existe un departamento con el ID indicado.
	 * 
	 * @param id identificador del departamento
	 * @return true si existe, false en caso contrario
	 */
	public boolean existsById(String id);
}