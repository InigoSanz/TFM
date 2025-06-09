package com.iem.tfm.infrastructure.apirest.dto.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO de entrada para el registro de solicitudes de vacaciones.
 * <p>
 * Esta clase representa la estructura del cuerpo de una petición HTTP {@code POST /vacations}.
 * Los datos recibidos se transforman posteriormente en un {@link com.iem.tfm.domain.command.VacationRegisterCommand}
 * para ser procesados en la capa de aplicación.
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
public class VacationRequestDto {
	
	private Date startDate;
	private Date endDate;
	private String employeeId;
}