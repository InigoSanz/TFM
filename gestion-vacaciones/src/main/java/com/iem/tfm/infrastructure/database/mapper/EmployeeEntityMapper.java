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
 * Mapper de la capa de infraestructura para convertir objetos del dominio a entidades de persistencia {@link EmployeeEntity}.
 * 
 * Se implementa utilizando MapStruct.
 * 
 * @author Inigo
 * @version 1.0
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = false), unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeEntityMapper {
	
	@Mapping(target ="departmentIds", source = "departments")
	public EmployeeEntity toEntity(Employee employee);

	public default List<Employee> toDomainList(List<EmployeeEntity> entities, List<Department> departments) {
		List<Employee> mapResult = new ArrayList<>();
		
		// Doble bucle
		for (EmployeeEntity entity : entities) {
			
			List<Department> employeeDepartments = new ArrayList<>();
			
			// Doble bucle
			for (String departmentId : entity.getDepartmentIds()) {
				for (Department department : departments) {
					if (department.getId().equals(departmentId)) {
						employeeDepartments.add(department);
						break;
					}
				}
			}
			
			// Creamos el empleado con toda la información de sus departamentos
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
	
	// Ahora tenemos que mapear los departamentos a los IDs
	public default List<String> departmentsToIds(List<Department> departments) {
		
		// Primero las validaciones
		if (departments == null) {
			return new ArrayList<>(); // Devolvemos un array vacio si nos llega nulo
		}
		
		List<String> departmentIds = new ArrayList<>();
		
		for (Department department : departments) {
			departmentIds.add(department.getId());
		}
		
		return departmentIds;
	}
}