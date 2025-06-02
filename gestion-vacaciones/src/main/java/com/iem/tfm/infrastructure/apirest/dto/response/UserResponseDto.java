package com.iem.tfm.infrastructure.apirest.dto.response;

import com.iem.tfm.domain.util.EmployeeRoleEnum;
import com.iem.tfm.domain.util.UserRoleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO de respuesta que representa a un usuario del sistema.
 * <p>
 * Esta clase se utiliza para enviar datos de usuario al cliente
 * en respuestas de la API REST. Contiene información básica del usuario,
 * incluyendo su rol, estado de activación y vínculo con el empleado correspondiente.
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
public class UserResponseDto {
	
	private String id;
	private String username;
	private UserRoleEnum role;
	private boolean userActive;
	private String employeeId;
	private EmployeeRoleEnum employeeRole;
}