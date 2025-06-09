package com.iem.tfm.infrastructure.apirest.dto.response;

import java.util.Date;
import java.util.List;

import com.iem.tfm.domain.model.VacationStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO de salida para representar una solicitud de vacaciones.
 * <p>
 * Esta clase es utilizada como respuesta en las peticiones HTTP relacionadas
 * con vacaciones, como por ejemplo {@code GET /vacations}, {@code GET
 * /vacations/{id}} o {@code GET /vacations/employee/{employeeId}}.
 * </p>
 *
 * <p>
 * Contiene los datos visibles al cliente tras la transformación del modelo de
 * dominio {@code Vacation} o de la entidad {@code VacationEntity}.
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
public class VacationResponseDto {

	private String id;
	private Date startDate;
	private Date endDate;
	private String employeeId;
	private VacationStatusEnum status;
	private List<String> departmentIds;
	private String employeeName;
}