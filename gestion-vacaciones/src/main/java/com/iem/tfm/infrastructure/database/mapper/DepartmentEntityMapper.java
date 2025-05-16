package com.iem.tfm.infrastructure.database.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.domain.model.Department;
import com.iem.tfm.infrastructure.database.entity.DepartmentEntity;

/**
 * Mapper de la capa de infraestructura para convertir objetos del dominio a entidades de persistencia {@link DepartmentEntity}.
 * 
 * Se implementa utilizando MapStruct.
 * 
 * @author Inigo
 * @version 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentEntityMapper {
	
	public Department toDomain(DepartmentEntity entity);
	
	public DepartmentEntity toEntity(Department domain);
	
	public List<Department> toDomainList(List<DepartmentEntity> entities);
	
	public List<DepartmentEntity> toEntityList(List<Department> domains);
}