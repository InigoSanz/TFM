package com.iem.tfm.infrastructure.database.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.iem.tfm.infrastructure.database.entity.EmployeeEntity;

/**
 * Repositorio de acceso a datos para {@link EmployeeEntity}, gestionado por
 * Spring Data MongoDB.
 * <p>
 * Permite operaciones CRUD y define una consulta personalizada por DNI. Se
 * utiliza en el adaptador que implementa el puerto de salida.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Repository
@EnableMongoRepositories
public interface EmployeeRepository extends MongoRepository<EmployeeEntity, String> {

	/**
	 * Verifica si existe un empleado por su DNI.
	 * 
	 * @param dni documento de identidad
	 * @return {@code true} si el empleado existe, {@code false} si no
	 */
	public boolean existsByDni(String dni);
	
	/**
	 * 
	 * @param departmentId
	 * @return
	 */
	public List<EmployeeEntity> findByDepartmentIdsIn(List<String> departmentId);
	
	/**
	 * 
	 * @param departmentId
	 * @param pageable
	 * @return
	 */
	public Page<EmployeeEntity> findByDepartmentIdsContaining(String departmentId, Pageable pageable);
}