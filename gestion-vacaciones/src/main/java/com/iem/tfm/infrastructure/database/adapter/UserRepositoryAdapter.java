package com.iem.tfm.infrastructure.database.adapter;

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
 * Adaptador del repositorio para usuarios que implementa el puerto de salida
 * {@link UserRepositoryOutputPort}.
 * <p>
 * Se encarga de interactuar con la base de datos utilizando
 * {@link UserRepository} y realizar la conversión entre entidades de
 * persistencia ({@link UserEntity}) y el modelo del dominio ({@link User}).
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Component
public class UserRepositoryAdapter implements UserRepositoryOutputPort {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserEntityMapper userEntityMapper;

	/**
	 * Guarda un usuario en la base de datos.
	 * 
	 * @param user objeto de dominio User a persistir
	 * @return identificador del usuario guardado
	 */
	@Override
	public String save(User user) {
		UserEntity entity = userEntityMapper.toEntity(user);
		UserEntity savedEntity = userRepository.save(entity);

		return savedEntity.getId();
	}

	/**
	 * Recupera un usuario a partir de su nombre de usuario.
	 * 
	 * @param username nombre de usuario
	 * @return objeto de dominio User
	 * @throws UserDomainException si el usuario no existe
	 */
	@Override
	public User findByUsername(String username) {
		Optional<UserEntity> entityOptional = userRepository.findByUsername(username);

		if (!entityOptional.isPresent()) {
			throw new UserDomainException("No se ha encontrado el usuario: " + username);
		}

		UserEntity entity = entityOptional.get();

		return userEntityMapper.toDomain(entity);
	}

	/**
	 * Verifica si existe un usuario con un nombre de usuario específico.
	 * 
	 * @param username nombre de usuario a comprobar
	 * @return true si el usuario existe, false en caso contrario
	 */
	@Override
	public boolean existsByUsername(String username) {

		return userRepository.existsByUsername(username);
	}

	/**
	 * Recupera todos los usuarios almacenados en la base de datos.
	 * 
	 * @return lista de usuarios como objetos de dominio User
	 */
	@Override
	public List<User> findAll() {
		List<UserEntity> entities = userRepository.findAll();

		return userEntityMapper.toDomainList(entities);
	}

	/**
	 * Recupera un usuario por su identificador.
	 * 
	 * @param id identificador único del usuario
	 * @return objeto de dominio User
	 * @throws UserDomainException si no se encuentra el usuario con ese ID
	 */
	@Override
	public User findById(String id) {
		Optional<UserEntity> entityOptional = userRepository.findById(id);

		if (!entityOptional.isPresent()) {
			throw new UserDomainException("Usuario no encontrado con id: " + id);
		}

		return userEntityMapper.toDomain(entityOptional.get());
	}
}