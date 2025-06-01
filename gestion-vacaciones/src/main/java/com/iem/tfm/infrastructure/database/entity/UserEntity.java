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
 * Entidad de base de datos que representa a un usuario en MongoDB.
 * <p>
 * Esta clase se utiliza para el almacenamiento y recuperación de credenciales
 * y datos de acceso del sistema. Equivale al modelo 
 * {@link com.iem.tfm.domain.model.User}, pero adaptado para persistencia.
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