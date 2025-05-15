package com.iem.tfm.infrastructure.database.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.domain.model.Department;
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
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = false), unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeEntityMapper {
	
	public EmployeeEntity toEntity(Employee employee);

	public default List<Employee> toDomainList(List<EmployeeEntity> entities) {
		List<Employee> mapResult = new ArrayList<>();
		
		for (EmployeeEntity entity : entities) {
			mapResult.add(toDomain(entity));
		}
		
		return mapResult;
		}

	public default Employee toDomain(EmployeeEntity entity) {
		return new Employee.Builder()
		        .id(entity.getId())
		        .name(entity.getName())
		        .surname(entity.getSurname())
		        .dni(entity.getDni())
		        .age(entity.getAge())
		        .email(entity.getEmail())
		        .startDate(entity.getStartDate())
		        .endDate(entity.getEndDate())
		        .departments(List.of(new Department("1L", "Departamento ficticio"))) // TODO: Mapear los departamentos reales
		        .role(entity.getRole())
		        .build();
	}
	
	// TODO: mapear List<Department> a List<String> (ids) para guardar en Mongo
}