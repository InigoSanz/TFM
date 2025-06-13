package com.iem.tfm.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.port.input.VacationRegisterInputPort;
import com.iem.tfm.application.port.output.EmployeeRepositoryOutputPort;
import com.iem.tfm.application.port.output.VacationRepositoryOutputPort;
import com.iem.tfm.domain.command.VacationRegisterCommand;
import com.iem.tfm.domain.exception.VacationDomainException;
import com.iem.tfm.domain.model.Employee;
import com.iem.tfm.domain.model.Vacation;
import com.iem.tfm.domain.model.VacationStatusEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * Servicio de aplicación para registrar nuevas solicitudes de vacaciones.
 * <p>
 * Implementa el caso de uso definido en {@link VacationRegisterInputPort} y
 * utiliza los puertos de salida {@link VacationRepositoryOutputPort} y
 * {@link EmployeeRepositoryOutputPort} para acceder a los datos necesarios.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Service
@Slf4j
public class VacationRegisterService implements VacationRegisterInputPort {

	@Autowired
	VacationRepositoryOutputPort vacationRepositoryOutput;

	@Autowired
	EmployeeRepositoryOutputPort employeeRepositoryOutput;

	/**
	 * Registra una nueva solicitud de vacaciones tras validar reglas de negocio.
	 * 
	 * @param command objeto {@link VacationRegisterCommand} con los datos de la
	 *                solicitud
	 * @return ID generado para la solicitud registrada
	 * @throws VacationDomainException si los datos no cumplen una regla de negocio
	 */
	@Override
	public String vacationRegister(VacationRegisterCommand command) {
		log.debug("-> Inicio registro de vacaciones <-");

		if (command.getStartDate().after(command.getEndDate())) {
			throw new VacationDomainException(
					"La fecha de inicio de las vacaciones no puede ser posterior a la de fin.");
		}

		if (!employeeRepositoryOutput.existsById(command.getEmployeeId())) {
			throw new VacationDomainException("El ID del empleado no es válido.");
		}

		List<Vacation> vacationOverlap = vacationRepositoryOutput
				.findByEmployeeIdAndDateOverlap(command.getEmployeeId(), command.getStartDate(), command.getEndDate());

		if (!vacationOverlap.isEmpty()) {
			throw new VacationDomainException("El empleado ya ha tiene vacaciones en esas fechas.");
		}

		Employee employee = employeeRepositoryOutput.findEmployeeById(command.getEmployeeId());

		List<String> departmentIds = employee.getDepartmentIds();

		if (departmentIds == null || departmentIds.isEmpty()) {
			throw new VacationDomainException("El empleado no pertenece a ningún departamento.");
		}

		Vacation vacation = new Vacation(null, command.getStartDate(), command.getEndDate(), command.getEmployeeId(),
				VacationStatusEnum.PENDIENTE_APROBACION_ENCARGADO, departmentIds, null); // En la primera solicitud no hay resolvedBy

		return vacationRepositoryOutput.save(vacation);
	}
}