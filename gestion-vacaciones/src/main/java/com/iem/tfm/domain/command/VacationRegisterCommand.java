package com.iem.tfm.domain.command;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Command para registrar una nueva solicitud de vacaciones.
 * <p>
 * Contiene los datos necesarios para realizar la operación de alta de vacaciones
 * desde la capa de aplicación.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationRegisterCommand {
	
	private Date startDate;
	private Date endDate;
	private String employeeId;
}