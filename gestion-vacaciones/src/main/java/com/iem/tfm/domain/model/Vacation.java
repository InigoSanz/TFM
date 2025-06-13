package com.iem.tfm.domain.model;

import java.util.Date;
import java.util.List;

import com.iem.tfm.domain.exception.VacationDomainException;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Modelo de dominio para las vacaciones.
 * 
 * Encapsula las fechas de inicio y fin de las vacaciones y al empleado al que
 * pertenecen.
 * 
 * @author Inigo
 * @version 1.1
 */
@Getter
@Setter // No deberiamos poner Setters en el dominio, pero lo necesitamos para cambiar el estado en el servicio de status change
@Builder
public class Vacation {

	private String id;
	private Date startDate;
	private Date endDate;
	private String employeeId;
	private VacationStatusEnum status;
	private List<String> departmentIds;
	private String resolvedBy;

	public Vacation(String id, Date startDate, Date endDate, String employeeId, VacationStatusEnum status,
			List<String> departmentIds, String resolvedBy) {

		if (employeeId == null) {
			throw new VacationDomainException("Las vacaciones tienen que estar asociadas a un empleado.");
		}

		if (startDate == null || endDate == null) {
			throw new VacationDomainException("Las fechas para las vacaciones no pueden ser nulas.");
		}

		if (startDate.after(endDate)) {
			throw new VacationDomainException("El inicio de las vacaciones no puede ser posterior a la de fin.");
		}

		if (status == null) {
			throw new VacationDomainException("Las vacaciones deben contener un estado.");
		}

		if (departmentIds == null || departmentIds.isEmpty()) {
			throw new VacationDomainException("Las vacaciones deben contener al menos un departamento.");
		}

		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employeeId = employeeId;
		this.status = status;
		this.departmentIds = departmentIds;
		this.resolvedBy = resolvedBy;
	}

	public void approveBySupervisor() {
		if (this.status != VacationStatusEnum.PENDIENTE_APROBACION_ENCARGADO) {
			throw new VacationDomainException("Solo puede aprobar la solicitud el encargado.");
		}

		this.status = VacationStatusEnum.PENDIENTE_APROBACION_RRHH;
	}

	public void approveByHhrr() {
		if (this.status != VacationStatusEnum.PENDIENTE_APROBACION_RRHH) {
			throw new VacationDomainException("Solo puede aprobar la solicitud RRHH.");
		}

		this.status = VacationStatusEnum.APROBADA;
	}

	public void solReject() {
		if (this.status == VacationStatusEnum.APROBADA || this.status == VacationStatusEnum.RECHAZADA) {
			throw new VacationDomainException("No se puede rechazar una solicitud que ya ha sido resuelta.");
		}

		this.status = VacationStatusEnum.RECHAZADA;
	}
}