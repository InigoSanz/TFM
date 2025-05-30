package com.iem.tfm.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.command.VacationStatusChangeCommand;
import com.iem.tfm.application.port.input.VacationStatusInputPort;
import com.iem.tfm.application.port.output.VacationRepositoryOutputPort;
import com.iem.tfm.domain.exception.VacationDomainException;
import com.iem.tfm.domain.model.Vacation;
import com.iem.tfm.domain.util.EmployeeRoleEnum;

/**
 * 
 */
@Service
public class VacationStatusService implements VacationStatusInputPort {

	@Autowired
	VacationRepositoryOutputPort vacationRepositoryOutput;
	
	@Override
	public void statusChange(VacationStatusChangeCommand command) {

		Vacation vacation = vacationRepositoryOutput.findVacationById(command.getVacationId());

		if (vacation == null) {
			throw new VacationDomainException("No se ha encontrado la solicitud de las vacaciones.");
		}

		EmployeeRoleEnum employeeRole = EmployeeRoleEnum.valueOf(command.getRole().toUpperCase());

		if (employeeRole == null) {
			throw new VacationDomainException("El empleado que solicita las vacaciones debe contener un rol.");
		}

		if (command.isApprove()) {
			switch (employeeRole) {
			case EmployeeRoleEnum.ENCARGADO -> {
				vacation.approveBySupervisor();
				
			}
			case EmployeeRoleEnum.RRHH -> {
				vacation.approveByHhrr();
				
			}
			default -> {
				throw new VacationDomainException(
						"Este rol no tiene permisos para aprobar la solicitud de las vacaciones.");
			}
			}
		} else {
			vacation.solReject();
		}

		vacationRepositoryOutput.save(vacation);
	}
}