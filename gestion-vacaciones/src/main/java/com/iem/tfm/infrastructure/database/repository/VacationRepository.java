package com.iem.tfm.infrastructure.database.repository;

import java.util.Date;
import java.util.List;

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
	
	// Actualización: En el adaptador de las vacaciones nos damos cuenta de que hay que validar
	// el solapamiento de las vacaciones, por lo que hay que implementar la query
	// Se utiliza la IA para esta consulta...
	List<VacationEntity> findByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(String employeeId, Date endDate, Date startDate);

	List<VacationEntity> findByEmployeeId(String employeeId);
}