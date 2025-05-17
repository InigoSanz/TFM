package com.iem.tfm.infrastructure.database.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * Entidad de persistencia para los departamentos en la base de datos MongoDB.
 * <p>
 * Esta clase se utiliza para el almacenamiento y recuperación de datos.
 * Equivale al modelo {@link com.iem.tfm.domain.model.Department}, pero adaptado
 * para que persista.
 * </p>
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