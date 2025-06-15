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
 * 
 */
@Service
public class EmployeeUpdateService implements EmployeeUpdateInputPort {

	@Autowired
	EmployeeRepositoryOutputPort employeeRepositoryOutput;

	@Autowired
	VacationRepositoryOutputPort vacationRepositoryOutput;

	/**
	 * 
	 */
	@Override
	public void updateEmployee(EmployeeUpdateCommand command) {

		// Habría que comprobar que existe aquí?
		Employee workingEmployee = employeeRepositoryOutput.findEmployeeById(command.getId());
		
		// No nos queda otra para los campos que vienen null en actualizaciones parciales hacer esto.
		// De esta manera conserva el valor anterior.
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

		// Cuando actualizamos los datos del empleado hay que actualizar las vacaciones
		// asociadas
		List<Vacation> vacations = vacationRepositoryOutput.findVacationByEmployeeId(command.getId());

		Date now = new Date(); // Recuperamos la fecha de hoy para la validación de más adelante

		for (Vacation vacation : vacations) {
			// Solo actualizamos las vacaciones si no han finalizado, ya que si han
			// finalizado viene bien tenerlas en el departamento anterior para un histórico
			if (vacation.getEndDate() == null || vacation.getEndDate().after(now)) {
				Vacation updatedVacation = Vacation.builder().id(vacation.getId()).employeeId(vacation.getEmployeeId())
						.startDate(vacation.getStartDate()).endDate(vacation.getEndDate()).status(vacation.getStatus())
						.departmentIds(updatedEmployee.getDepartmentIds())
						// Con el resolvedBy habría también que actualizar la lógica, ya que si la ha
						// resuelto el ecnargado del departamento anterior, podría llevar a
						// inconsistencias en la organizacion de la empresa
						.resolvedBy(vacation.getResolvedBy()).build();
				vacationRepositoryOutput.save(updatedVacation);
			}
		}
	}
}