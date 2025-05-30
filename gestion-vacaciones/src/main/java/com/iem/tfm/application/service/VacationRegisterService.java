package com.iem.tfm.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.command.VacationRegisterCommand;
import com.iem.tfm.application.port.input.VacationRegisterInputPort;
import com.iem.tfm.application.port.output.EmployeeRepositoryOutputPort;
import com.iem.tfm.application.port.output.VacationRepositoryOutputPort;
import com.iem.tfm.domain.exception.VacationDomainException;
import com.iem.tfm.domain.model.Vacation;
import com.iem.tfm.domain.util.VacationStatusEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Service
@Slf4j
public class VacationRegisterService implements VacationRegisterInputPort {

	@Autowired
	VacationRepositoryOutputPort vacationRepositoryOutput;

	@Autowired
	EmployeeRepositoryOutputPort employeeRepositoryOutput;

	@Override
	public String vacationRegister(VacationRegisterCommand command) {
		log.info("-> Inicio registro de vacaciones <-");

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

		Vacation vacation = new Vacation(null, command.getStartDate(), command.getEndDate(), command.getEmployeeId(), VacationStatusEnum.PENDIENTE_APROBACION_ENCARGADO);

		return vacationRepositoryOutput.save(vacation);
	}
}
