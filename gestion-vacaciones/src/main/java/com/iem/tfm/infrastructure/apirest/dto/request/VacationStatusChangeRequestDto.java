package com.iem.tfm.infrastructure.apirest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO utilizado para solicitar un cambio en el estado de una solicitud de
 * vacaciones.
 * <p>
 * Este DTO se recibe a través de la capa de API REST, en particular en el
 * controlador
 * {@link com.iem.tfm.infrastructure.apirest.controller.VacationController}
 * mediante el endpoint PATCH <code>/vacations/{vacation-id}/status</code>.
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
public class VacationStatusChangeRequestDto {

	private String employeeId;
	private String role;
	private boolean approve;
}