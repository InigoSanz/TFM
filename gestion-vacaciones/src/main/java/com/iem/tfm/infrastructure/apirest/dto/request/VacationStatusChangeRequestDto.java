package com.iem.tfm.infrastructure.apirest.dto.request;

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
public class VacationStatusChangeRequestDto {
	
	private String employeeId;
	private String role;
	private boolean approve;
}