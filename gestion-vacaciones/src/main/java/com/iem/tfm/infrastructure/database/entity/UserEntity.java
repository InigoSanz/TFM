package com.iem.tfm.infrastructure.database.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.iem.tfm.domain.util.UserRoleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * 
 */
@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Document("USERS")
public class UserEntity {
	
	@Id
	private String id;
	private String username;
	private String password;
	private UserRoleEnum role;
	private boolean userActive;
	private String employeeId;
}