package com.iem.tfm.infrastructure.apirest.dto.request;

import java.util.Date;
import java.util.List;

import com.iem.tfm.domain.util.EmployeeRoleEnum;

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
public class EmployeeRequestDto {
	
	private String name;
	private String surname;
	private String dni;
	private int age;
	private String email;
	private Date startDate;
	private Date endDate;
	private List<Long> departmentIds;
	private EmployeeRoleEnum role;	
}