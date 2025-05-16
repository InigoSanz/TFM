package com.iem.tfm.infrastructure.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.iem.tfm.infrastructure.database.entity.DepartmentEntity;

public interface DepartmentRepository extends MongoRepository<DepartmentEntity, String> {
	
	public boolean existsById();
}