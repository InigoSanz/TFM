package com.iem.tfm.infrastructure.database.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.domain.model.Employee;
import com.iem.tfm.infrastructure.database.entity.EmployeeEntity;

/**
 * Mapper de la capa de infraestructura para convertir objetos del dominio a entidades de persistencia {@link EmployeeEntity}.
 * 
 * Se implementa utilizando MapStruct.
 * 
 * @author Inigo
 * @version 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeEntityMapper {
	
	public EmployeeEntity toEntity(Employee employee);
	
	// TODO: mapear List<Department> a List<String> (ids) para guardar en Mongo
}