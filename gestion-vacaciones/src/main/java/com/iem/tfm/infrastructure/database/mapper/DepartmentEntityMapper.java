package com.iem.tfm.infrastructure.database.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.domain.model.Department;
import com.iem.tfm.infrastructure.database.entity.DepartmentEntity;

/**
 * Mapper de infraestructura que convierte entre {@link Department} (modelo de
 * dominio) y {@link DepartmentEntity} (entidad de base de datos para MongoDB).
 * <p>
 * Se implementa utilizando MapStruct.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentEntityMapper {

	/**
	 * Convierte una entidad de base de datos a un objeto del dominio.
	 * 
	 * @param entity entidad almacenada en MongoDB
	 * @return objeto de dominio
	 */
	default Department toDomain(DepartmentEntity entity) {
	    if (entity == null) return null;
	    return new Department(entity.getId(), entity.getName());
	}

	/**
	 * Convierte un objeto del dominio a una entidad para persistencia.
	 * 
	 * @param domain objeto del dominio
	 * @return entidad lista para guardar en MongoDB
	 */
	public DepartmentEntity toEntity(Department domain);

	/**
	 * Convierte una lista de entidades a una lista de objetos del dominio.
	 * 
	 * @param entities lista de entidades de MongoDB
	 * @return lista de departamentos del dominio
	 */
	public List<Department> toDomainList(List<DepartmentEntity> entities);

	/**
	 * Convierte una lista de objetos del dominio a entidades.
	 * 
	 * @param domains lista de departamentos del dominio
	 * @return lista de entidades para almacenar
	 */
	public List<DepartmentEntity> toEntityList(List<Department> domains);
}