package com.iem.tfm.infrastructure.apirest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * Esta clase la utilizaremos más adelante para que el usuario pueda cambiar su password generada por defecto.
 */
@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordChangeRequestDto {
	
	private String newPassword;
}