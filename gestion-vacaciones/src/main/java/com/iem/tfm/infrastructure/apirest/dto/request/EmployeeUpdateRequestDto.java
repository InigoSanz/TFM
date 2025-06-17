package com.iem.tfm.infrastructure.apirest.dto.request;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * 
 */
@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdateRequestDto {
	
	private int age; // Cambiar al Date para fecha de nacimiento
	private String email;
	private Date startDate;
	private Date endDate;
	private List<String> departmentIds;
	private String role;
}