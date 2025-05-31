package com.iem.tfm.infrastructure.database.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.iem.tfm.infrastructure.database.entity.UserEntity;

/**
 * 
 */
@Repository
@EnableMongoRepositories
public interface UserRepository extends MongoRepository<UserEntity, String> {
	
	public Optional<UserEntity> findByUsername(String username);
	
	public boolean existsByUsername(String username);
}