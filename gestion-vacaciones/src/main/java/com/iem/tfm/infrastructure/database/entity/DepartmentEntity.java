package com.iem.tfm.infrastructure.database.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * Entidad para el almacenamiento de los departamentos en MongoDB.
 * 
 * @author Inigo
 * @version 1.0
 */
@Document("DEPARTMENTS")
@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentEntity {
	
	@Id
	private String id;
	private String name;
}