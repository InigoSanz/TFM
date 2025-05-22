package com.iem.tfm.infrastructure.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.iem.tfm.infrastructure.database.entity.VacationEntity;

/**
 * 
 */
@Repository
@EnableMongoRepositories
public interface VacationRepository extends MongoRepository<VacationEntity, String> {

	// Aquí creo que en un futuro habrá que comprobar también el solapamiento de
	// fechas en la BBDD al igual que lo hemos hecho en la capa de aplicación
}