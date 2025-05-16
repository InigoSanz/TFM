package com.iem.tfm.infrastructure.database.repository.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iem.tfm.application.port.output.DepartmentRepositoryOutputPort;
import com.iem.tfm.domain.model.Department;
import com.iem.tfm.infrastructure.database.entity.DepartmentEntity;
import com.iem.tfm.infrastructure.database.mapper.DepartmentEntityMapper;
import com.iem.tfm.infrastructure.database.repository.DepartmentRepository;

/**
 * Adaptador para conectar el puerto de salida con la infraestructura
 * {@link DepartmentRepositoryOutputPort}.
 * 
 * Convierte la entidades en documentos de MongoDB mediante un mapper.
 * 
 * Delega las operaciones en el repositorio Mongo {@link DepartmentRepository}.
 * 
 * Punte entre la aplicación y la persistencia en BBDD.
 * 
 * @author Inigo
 * @version 1.0
 */
@Repository
public class DepartmentRepositoryAdapter implements DepartmentRepositoryOutputPort {

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	DepartmentEntityMapper departmentEntityMapper;

	@Override
	public List<Department> findAllById(List<String> departmentIds) {

		List<DepartmentEntity> entities = departmentRepository.findAllById(departmentIds);

		return departmentEntityMapper.toDomainList(entities);
	}

	@Override
	public List<Department> findAll() {
		
		List<DepartmentEntity> entities = departmentRepository.findAll();
		
		return departmentEntityMapper.toDomainList(entities);
	}
}