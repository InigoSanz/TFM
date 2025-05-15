package com.iem.tfm.infrastructure.apirest.dto.response;

import java.util.Date;
import java.util.List;

import com.iem.tfm.domain.util.EmployeeRoleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO de salida para la obtención de empleados.
 * 
 * 
 * @author Inigo
 * @version 1.0
 */
@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {
	
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