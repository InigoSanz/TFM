package com.iem.tfm.infrastructure.database.mapper;

import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.domain.model.Employee;
import com.iem.tfm.infrastructure.database.entity.EmployeeEntity;

/**
 * Mapper de infraestructura que convierte entre {@link Employee} (modelo de
 * dominio) y {@link EmployeeEntity} (entidad de base de datos para MongoDB).
 * <p>
 * Se implementa utilizando MapStruct y lógica adicional personalizada.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = false), unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeEntityMapper {

	/**
	 * Mapea un objeto de dominio a su entidad para persistencia. Convierte los
	 * departamentos en una lista de IDs.
	 * 
	 * @param employee objeto del dominio
	 * @return entidad para guardar en Mongo
	 */
	public EmployeeEntity toEntity(Employee employee);

	/**
	 * Convierte una entidad a objeto del dominio.
	 * 
	 * @param entity      entidad almacenada
	 * @param departments lista completa de departamentos
	 * @return empleado del dominio
	 */
	public Employee toDomain(EmployeeEntity entity);

	/**
	 * Lista de entidades a lista de modelos de dominio.
	 * 
	 * @param entities lista de entidades
	 * @return lista de entidades de dominio
	 */
	public List<Employee> toDomainList(List<EmployeeEntity> entities);
}