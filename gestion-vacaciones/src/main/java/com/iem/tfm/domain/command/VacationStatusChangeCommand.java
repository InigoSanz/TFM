package com.iem.tfm.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Command para cambiar el estado de una solicitud de vacaciones.
 * <p>
 * Este objeto encapsula la información necesaria para aprobar o rechazar una
 * solicitud de vacaciones, en función del rol del usuario que realiza la
 * acción.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VacationStatusChangeCommand {

	private String vacationId;
	private String employeeId;
	private String role;
	private boolean approve;
}