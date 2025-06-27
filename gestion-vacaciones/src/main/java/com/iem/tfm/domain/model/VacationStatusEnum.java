package com.iem.tfm.domain.model;

/**
 * Enumeración que representa los distintos estados posibles de una solicitud de
 * vacaciones.
 * <p>
 * Este estado se utiliza para controlar el flujo de aprobación y gestión de las
 * solicitudes dentro del sistema.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public enum VacationStatusEnum {
	PENDIENTE_APROBACION_ENCARGADO, PENDIENTE_APROBACION_RRHH, RECHAZADA, CANCELADA, APROBADA
}
