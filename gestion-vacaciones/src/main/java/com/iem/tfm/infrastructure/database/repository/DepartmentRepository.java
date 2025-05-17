package com.iem.tfm.infrastructure.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.iem.tfm.infrastructure.database.entity.DepartmentEntity;

/**
 * 
 */
@Repository
@EnableMongoRepositories
public interface DepartmentRepository extends MongoRepository<DepartmentEntity, String> {
	
	public boolean existsById();
}