package com.iem.tfm.infrastructure.database.repository.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iem.tfm.application.port.output.DepartmentRepositoryOutputPort;
import com.iem.tfm.domain.exception.DepartmentDomainException;
import com.iem.tfm.domain.model.Department;
import com.iem.tfm.infrastructure.database.entity.DepartmentEntity;
import com.iem.tfm.infrastructure.database.mapper.DepartmentEntityMapper;
import com.iem.tfm.infrastructure.database.repository.DepartmentRepository;

/**
 * Adaptador que implementa el puerto de salida
 * {@link DepartmentRepositoryOutputPort}.
 * 
 * <p>
 * Se encarga de interactuar con la base de datos MongoDB usando
 * {@link DepartmentRepository} y convierte entre entidades y modelos del
 * dominio con {@link DepartmentEntityMapper}.
 * </p>
 * 
 * <p>
 * Actúa como puente entre la lógica de aplicación y la infraestructura de
 * persistencia.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Component
public class DepartmentRepositoryAdapter implements DepartmentRepositoryOutputPort {

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	DepartmentEntityMapper departmentEntityMapper;

	/**
	 * Busca una lista de departamentos por sus IDs.
	 * 
	 * @param departmentIds lista de IDs
	 * @return lista de departamentos del dominio
	 */
	@Override
	public List<Department> findAllById(List<String> departmentIds) {

		List<DepartmentEntity> entities = departmentRepository.findAllById(departmentIds);

		return departmentEntityMapper.toDomainList(entities);
	}

	/**
	 * Devuelve todos los departamentos almacenados.
	 * 
	 * @return lista completa de departamentos del dominio
	 */
	@Override
	public List<Department> findAll() {

		List<DepartmentEntity> entities = departmentRepository.findAll();

		return departmentEntityMapper.toDomainList(entities);
	}

	/**
	 * Busca un departamento por ID.
	 * 
	 * @param id identificador del departamento
	 * @return departamento correspondiente
	 * @throws DepartmentDomainException si no se encuentra
	 */
	@Override
	public Department findDepartmentById(String id) {

		Optional<DepartmentEntity> entityOptional = departmentRepository.findById(id);

		if (!entityOptional.isPresent()) {
			throw new DepartmentDomainException("Departamento no encontrado con id: " + id);
		}

		DepartmentEntity entity = entityOptional.get();
		return departmentEntityMapper.toDomain(entity);
	}
}