package com.iem.tfm.infrastructure.apirest.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO de respuesta para el proceso de registro masivo de empleados mediante
 * archivo Excel.
 * 
 * <p>
 * Este DTO es utilizado por el endpoint {@code POST /employee/batch-upload}.
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
public class EmployeeBatchRegisterResponseDto {

	private int total;
	private List<String> registerEmployees;
	private List<String> errors;
}