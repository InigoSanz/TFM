package com.iem.tfm.infrastructure.apirest.dto.response;

import java.util.List;

import com.iem.tfm.domain.model.EmployeeRoleEnum;
import com.iem.tfm.domain.model.UserRoleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO que representa la respuesta enviada al cliente tras un login exitoso.
 * <p>
 * Contiene la información esencial del usuario autenticado, que puede ser
 * utilizada por el frontend para gestionar el estado de sesión y las vistas
 * según el rol del usuario.
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
public class LoginResponseDto {

	private String userId;
	private String username;
	private UserRoleEnum role;
	private boolean userActive;
	private String employeeId;
	private EmployeeRoleEnum employeeRole;
	private List<String> departmentNames;
	private List<String> departmentIds;
}