package com.iem.tfm.infrastructure.database.repository.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iem.tfm.application.port.output.UserRepositoryOutputPort;
import com.iem.tfm.domain.exception.UserDomainException;
import com.iem.tfm.domain.model.User;
import com.iem.tfm.infrastructure.database.entity.UserEntity;
import com.iem.tfm.infrastructure.database.mapper.UserEntityMapper;
import com.iem.tfm.infrastructure.database.repository.UserRepository;

/**
 * 
 */
@Component
public class UserRepositoryAdapter implements UserRepositoryOutputPort {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserEntityMapper userEntityMapper;

	@Override
	public String save(User user) {
		UserEntity entity = userEntityMapper.toEntity(user);
		UserEntity savedEntity = userRepository.save(entity);

		return savedEntity.getId();
	}

	@Override
	public User findByUsername(String username) {
		Optional<UserEntity> entityOptional = userRepository.findByUsername(username);

		if (!entityOptional.isPresent()) {
			throw new UserDomainException("No se ha encontrado el usuario: " + username);
		}

		UserEntity entity = entityOptional.get();

		return userEntityMapper.toDomain(entity);
	}

	@Override
	public boolean existsByUsername(String username) {

		return userRepository.existsByUsername(username);
	}

	@Override
	public List<User> findAll() {
		List<UserEntity> entities = userRepository.findAll();

		return userEntityMapper.toDomainList(entities);
	}

	@Override
	public User findById(String id) {
		Optional<UserEntity> entityOptional = userRepository.findById(id);

		if (!entityOptional.isPresent()) {
			throw new UserDomainException("Usuario no encontrado con id: " + id);
		}

		return userEntityMapper.toDomain(entityOptional.get());
	}
}