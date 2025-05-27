package com.iem.tfm.infrastructure.database.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.domain.model.Department;
import com.iem.tfm.domain.model.Employee;
import com.iem.tfm.infrastructure.database.entity.EmployeeEntity;

/**
 * Mapper de infraestructura que convierte entre {@link Employee} (modelo de dominio)
 * y {@link EmployeeEntity} (entidad de base de datos para MongoDB).
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
	 * Mapea un objeto de dominio a su entidad para persistencia.
	 * Convierte los departamentos en una lista de IDs.
	 * 
	 * @param employee objeto del dominio
	 * @return entidad para guardar en Mongo
	 */
	@Mapping(target ="departmentIds", source = "departments")
	public EmployeeEntity toEntity(Employee employee);
	
	/**
	 * Convierte una lista de entidades a una lista de objetos de dominio,
	 * matcheando los IDs de departamentos con sus objetos completos.
	 * 
	 * @param entities lista de entidades
	 * @param departments todos los departamentos
	 * @return lista de empleados reconstruidos con sus departamentos completos
	 */
	public default List<Employee> toDomainList(List<EmployeeEntity> entities, List<Department> departments) {
		List<Employee> mapResult = new ArrayList<>();
		
		// Recorremos cada entiedad de empleado
		for (EmployeeEntity entity : entities) {	
			// Lista para guardar los departamentos completos del empleado
			List<Department> employeeDepartments = new ArrayList<>();
			// Por cada ID de departamento de la entidad...
			for (String departmentId : entity.getDepartmentIds()) {
				// ...buscamos el departamento completo correspondiente
				for (Department department : departments) {
					if (department.getId().equals(departmentId)) {
						// Si coincide el ID, lo añadimos a la lista del empleado
						employeeDepartments.add(department);
						break; // Salimos del bucle interno
					}
				}
			}
			
			Employee employee = new Employee.Builder()
					.id(entity.getId())
	                .name(entity.getName())
	                .surname(entity.getSurname())
	                .dni(entity.getDni())
	                .age(entity.getAge())
	                .email(entity.getEmail())
	                .startDate(entity.getStartDate())
	                .endDate(entity.getEndDate())
	                .departments(employeeDepartments)
	                .role(entity.getRole())
	                .build();
			
			mapResult.add(employee);
		}
		
		return mapResult;
	}
	
	/**
	 * Convierte una entidad a objeto del dominio.
	 * 
	 * @param entity entidad almacenada
	 * @param departments lista completa de departamentos
	 * @return empleado del dominio
	 */
	public default Employee toDomain(EmployeeEntity entity, List<Department> departments) {
		return new Employee.Builder()
		        .id(entity.getId())
		        .name(entity.getName())
		        .surname(entity.getSurname())
		        .dni(entity.getDni())
		        .age(entity.getAge())
		        .email(entity.getEmail())
		        .startDate(entity.getStartDate())
		        .endDate(entity.getEndDate())
		        .departments(departments)
		        .role(entity.getRole())
		        .build();
	}
	
	/**
	 * Convierte una lista de objetos {@link Department} a una lista de sus IDs.
	 * 
	 * @param departments lista de departamentos
	 * @return lista de IDs de departamentos
	 */
	public default List<String> departmentsToIds(List<Department> departments) {
		
		if (departments == null) {
			return new ArrayList<>();
		}
		
		List<String> departmentIds = new ArrayList<>();
		
		for (Department department : departments) {
			departmentIds.add(department.getId());
		}
		
		return departmentIds;
	}
}