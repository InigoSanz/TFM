package com.iem.tfm.infrastructure.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.iem.tfm.infrastructure.database.entity.EmployeeEntity;

/**
 * 
 */
@Repository
@EnableMongoRepositories
public interface EmployeeRepository extends MongoRepository<EmployeeEntity, String>{

	public boolean existsByDni(String dni);
}