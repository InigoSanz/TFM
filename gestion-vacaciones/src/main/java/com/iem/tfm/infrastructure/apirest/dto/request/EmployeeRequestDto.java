package com.iem.tfm.infrastructure.apirest.dto.request;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO de entrada para la creación de empleados.
 * 
 * Se transformará a un {@code EmployeeRegisterCommand} por un mapper.
 * 
 * @author Inigo
 * @version 1.0
 */
@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDto {
	
	private String name;
	private String surname;
	private String dni;
	private int age;
	private String email;
	private Date startDate;
	private Date endDate;
	private List<String> departmentIds;
	private String role;	// Aquí me acuerdo que es mejor trabajar con String, lo cambiamos y en el mapper lo mapearemos al Enum
}