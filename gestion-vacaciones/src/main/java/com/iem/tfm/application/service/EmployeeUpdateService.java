package com.iem.tfm.application.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.port.input.EmployeeUpdateInputPort;
import com.iem.tfm.application.port.output.EmployeeRepositoryOutputPort;
import com.iem.tfm.application.port.output.VacationRepositoryOutputPort;
import com.iem.tfm.domain.command.EmployeeUpdateCommand;
import com.iem.tfm.domain.model.Employee;
import com.iem.tfm.domain.model.EmployeeRoleEnum;
import com.iem.tfm.domain.model.Vacation;

/**
 * Servicio de aplicación para la actualización de empleados.
 * <p>
 * Implementa el caso de uso definido en {@link EmployeeUpdateInputPort},
 * permitiendo actualizar los datos de un empleado existente, conservando la
 * información anterior en caso de que algunos campos no se proporcionen.
 * </p>
 * <p>
 * Además, si el empleado tiene vacaciones activas (no finalizadas), estas se
 * actualizan con los nuevos departamentos del empleado para mantener la
 * coherencia.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Service
public class EmployeeUpdateService implements EmployeeUpdateInputPort {

	@Autowired
	EmployeeRepositoryOutputPort employeeRepositoryOutput;

	@Autowired
	VacationRepositoryOutputPort vacationRepositoryOutput;

	/**
	 * Actualiza los datos de un empleado existente con los valores proporcionados
	 * en el comando. Si algún campo del comando es {@code null}, se conserva el
	 * valor actual del empleado.
	 * 
	 * También actualiza las vacaciones activas del empleado con los nuevos
	 * departamentos.
	 *
	 * @param command objeto que contiene los nuevos datos del empleado
	 */
	@Override
	public void updateEmployee(EmployeeUpdateCommand command) {

		Employee workingEmployee = employeeRepositoryOutput.findEmployeeById(command.getId());

		// Construimos el nuevo objeto actualizando solo los campos no nulos
		Employee updatedEmployee = Employee.builder().id(workingEmployee.getId()).name(workingEmployee.getName())
				.surname(workingEmployee.getSurname()).dni(workingEmployee.getDni())
				.age(Optional.ofNullable(command.getAge()).orElse(workingEmployee.getAge()))
				.email(Optional.ofNullable(command.getEmail()).orElse(workingEmployee.getEmail()))
				.startDate(Optional.ofNullable(command.getStartDate()).orElse(workingEmployee.getStartDate()))
				.endDate(Optional.ofNullable(command.getEndDate()).orElse(workingEmployee.getEndDate()))
				.departmentIds(
						Optional.ofNullable(command.getDepartmentIds()).orElse(workingEmployee.getDepartmentIds()))
				.role(Optional.ofNullable(command.getRole()).map(EmployeeRoleEnum::valueOf)
						.orElse(workingEmployee.getRole()))
				.build();

		employeeRepositoryOutput.save(updatedEmployee);

		// Actualizar las vacaciones activas del empleado con los nuevos departamentos
		List<Vacation> vacations = vacationRepositoryOutput.findVacationByEmployeeId(command.getId());
		Date now = new Date();

		for (Vacation vacation : vacations) {
			if (vacation.getEndDate() == null || vacation.getEndDate().after(now)) {
				Vacation updatedVacation = Vacation.builder().id(vacation.getId()).employeeId(vacation.getEmployeeId())
						.startDate(vacation.getStartDate()).endDate(vacation.getEndDate()).status(vacation.getStatus())
						.departmentIds(updatedEmployee.getDepartmentIds()).resolvedBy(vacation.getResolvedBy()).build();

				vacationRepositoryOutput.save(updatedVacation);
			}
		}
	}
}