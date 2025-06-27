package com.iem.tfm.infrastructure.apirest.dto.request;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO de solicitud para la actualización parcial de los datos de un empleado.
 * <p>
 * Nota: actualmente el campo {@code age} representa la edad como número entero,
 * pero podría sustituirse en el futuro por una fecha de nacimiento.
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
public class EmployeeUpdateRequestDto {

	private int age; // Cambiar al Date para fecha de nacimiento
	private String email;
	private Date startDate;
	private Date endDate;
	private List<String> departmentIds;
	private String role;
}