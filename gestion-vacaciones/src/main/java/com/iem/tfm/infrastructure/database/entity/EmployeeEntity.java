package com.iem.tfm.infrastructure.database.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.iem.tfm.domain.util.EmployeeRoleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * Entidad para el almacenamiento de los empleados en MongoDB.
 * 
 * @author Inigo
 * @version 1.0
 */
@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Document("EMPLOYEES")
public class EmployeeEntity {
	
	@Id
	private Long id;
	private String name;
	private String surname;
	private String dni;
	private int age;
	private String email;
	private Date startDate;
	private Date endDate;
	private List<Long> departmentIds; // En el dominio tenemos los departamentos, en Mongo con los IDs es suficiente
	private EmployeeRoleEnum role;	
}