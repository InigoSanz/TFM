package com.iem.tfm.infrastructure.apirest.dto.response;

import com.iem.tfm.domain.util.UserRoleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

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
}