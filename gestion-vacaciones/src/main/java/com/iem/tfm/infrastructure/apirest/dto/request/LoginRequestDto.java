package com.iem.tfm.infrastructure.apirest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO que representa los datos necesarios para realizar una petición de login.
 * <p>
 * Este objeto es enviado desde el frontend y contiene las credenciales del
 * usuario que intenta autenticarse en el sistema.
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
public class LoginRequestDto {

	private String username;
	private String password;
}