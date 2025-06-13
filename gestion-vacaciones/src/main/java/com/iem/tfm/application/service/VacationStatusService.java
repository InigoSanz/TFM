package com.iem.tfm.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.port.input.VacationStatusInputPort;
import com.iem.tfm.application.port.output.VacationRepositoryOutputPort;
import com.iem.tfm.domain.command.VacationStatusChangeCommand;
import com.iem.tfm.domain.exception.VacationDomainException;
import com.iem.tfm.domain.model.EmployeeRoleEnum;
import com.iem.tfm.domain.model.Vacation;

import lombok.extern.slf4j.Slf4j;

/**
 * Servicio de aplicación para gestionar el cambio de estado de solicitudes de
 * vacaciones.
 * <p>
 * Implementa el caso de uso definido en {@link VacationStatusInputPort} y
 * utiliza el puerto de salida {@link VacationRepositoryOutputPort} para acceder
 * y modificar los datos persistidos de vacaciones.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Service
@Slf4j
public class VacationStatusService implements VacationStatusInputPort {

	@Autowired
	VacationRepositoryOutputPort vacationRepositoryOutput;

	/**
	 * Cambia el estado de una solicitud de vacaciones, validando el rol del usuario
	 * que realiza la acción.
	 * 
	 * @param command objeto {@link VacationStatusChangeCommand} que contiene los
	 *                datos para la decisión
	 * @throws VacationDomainException lanza la excepción si incumple una regla
	 */
	@Override
	public void statusChange(VacationStatusChangeCommand command) {
		log.debug("-> Inicio cambio de estado de vacaciones <-");

		Vacation vacation = vacationRepositoryOutput.findVacationById(command.getVacationId());

		if (vacation == null) {
			throw new VacationDomainException("No se ha encontrado la solicitud de las vacaciones.");
		}

		EmployeeRoleEnum employeeRole = EmployeeRoleEnum.valueOf(command.getRole().toUpperCase());

		if (employeeRole == null) {
			throw new VacationDomainException("El empleado que solicita las vacaciones debe contener un rol.");
		}

		// Cambiar el estado según el rol
		if (command.isApprove()) {
			switch (employeeRole) {
				case EmployeeRoleEnum.ENCARGADO -> vacation.approveBySupervisor();
				case EmployeeRoleEnum.RRHH -> vacation.approveByHhrr();
				default -> throw new VacationDomainException("Este rol no tiene permisos para aprobar la solicitud.");
			}
		} else {
			vacation.solReject();
		}

		// Guardar el resolvedor si está presente
		String resolvedBy = command.getResolvedByName();
		if (resolvedBy != null && !resolvedBy.isBlank()) {
			vacationRepositoryOutput.setResolvedBy(command.getVacationId(), resolvedBy);
			log.debug("-> Solicitud resuelta por: {} <-", resolvedBy);
		}

		log.debug("-> Estado de las vacaciones cambiado exitosamente <-");
	}

}