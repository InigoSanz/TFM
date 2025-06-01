package com.iem.tfm.infrastructure.database.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.iem.tfm.domain.util.VacationStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * Entidad de base de datos que representa una solicitud de vacaciones.
 * <p>
 * Esta clase es utilizada por MongoDB para mapear documentos de la colección {@code VACATIONS}.
 * Se corresponde con el modelo de dominio {@code Vacation}, y contiene todos los
 * atributos necesarios para su persistencia.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Document("VACATIONS")
@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class VacationEntity {
	
	@Id
	private String id;
	private Date startDate;
	private Date endDate;
	private String employeeId;
	private VacationStatusEnum status;
}