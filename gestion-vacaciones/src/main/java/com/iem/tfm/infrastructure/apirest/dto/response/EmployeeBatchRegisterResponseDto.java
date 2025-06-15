package com.iem.tfm.infrastructure.apirest.dto.response;

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
public class EmployeeBatchRegisterResponseDto {
	
	private int total;
	private List<String> registerEmployees;
	private List<String> errors;
}