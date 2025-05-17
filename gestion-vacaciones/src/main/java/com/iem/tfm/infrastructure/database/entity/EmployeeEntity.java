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
 * Entidad de base de datos que representa a un empleado en MongoDB.
 * <p>
 * Esta clase se utiliza para el almacenamiento y recuperación de datos.
 * Equivale al modelo {@link com.iem.tfm.domain.model.Employee}, pero adaptado para que persista.
 * </p>
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
	private String id;
	private String name;
	private String surname;
	private String dni;
	private int age;
	private String email;
	private Date startDate;
	private Date endDate;
	private List<String> departmentIds;
	private EmployeeRoleEnum role;	
}