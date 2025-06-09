package com.iem.tfm.infrastructure.apirest.dto.request;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO de entrada para crear un nuevo empleado desde una API externa.
 * <p>
 * Este objeto se transforma a un {@link com.iem.tfm.domain.command.EmployeeRegisterCommand}
 * mediante un mapper en la capa de infraestructura.
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
public class EmployeeRequestDto {
	
	private String name;
	private String surname;
	private String dni;
	private int age;
	private String email;
	private Date startDate;
	private Date endDate;
	private List<String> departmentIds;
	private String role; // En vez de trabajar con Enum, es buena práctica trabajar como texto y mapearlo
}