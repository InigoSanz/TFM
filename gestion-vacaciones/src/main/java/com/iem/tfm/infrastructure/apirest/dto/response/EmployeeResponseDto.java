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
 * DTO de salida para representar la información de un empleado en la respuesta de una API.
 * <p>
 * Se utiliza cuando se devuelven empleados al cliente, ya sea de forma individual o en listas.
 * Contiene información personal, departamentos y rol del empleado.
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
public class EmployeeResponseDto {
	
	private String id;
	private String name;
	private String surname;
	private String dni;
	private int age;
	private String email;
	private Date startDate;
	private Date endDate;
	private List<DepartmentResponseDto> departments;
	private EmployeeRoleEnum role;
}