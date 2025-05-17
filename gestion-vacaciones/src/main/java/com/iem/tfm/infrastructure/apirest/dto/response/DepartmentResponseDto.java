package com.iem.tfm.infrastructure.apirest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO de salida para representar un departamento en las respuestas de la API.
 * <p>
 * Se utiliza para devolver la información básica de un departamento al cliente,
 * por ejemplo en operaciones de consulta de empleados o de departamentos.
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
public class DepartmentResponseDto {

	private String id;
	private String name;
}