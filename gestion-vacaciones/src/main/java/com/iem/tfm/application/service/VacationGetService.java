package com.iem.tfm.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.port.input.VacationGetInputPort;
import com.iem.tfm.application.port.output.VacationRepositoryOutputPort;
import com.iem.tfm.domain.model.Vacation;

import lombok.extern.slf4j.Slf4j;

/**
 * Servicio de aplicación para consultar solicitudes de vacaciones.
 * <p>
 * Implementa el caso de uso definido en {@link VacationGetInputPort} y accede a
 * los datos mediante el puerto de salida {@link VacationRepositoryOutputPort}.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Service
@Slf4j
public class VacationGetService implements VacationGetInputPort {

	@Autowired
	VacationRepositoryOutputPort vacationRepositoryOutput;

	/**
	 * Recupera todas las solicitudes de vacaciones del sistema.
	 * 
	 * @return lista de vacaciones
	 */
	@Override
	public List<Vacation> getAllVacation() {
		log.debug("-> Obteniendo todas las vacaciones <-");

		return vacationRepositoryOutput.findAll();
	}

	/**
	 * Recupera una solicitud de vacaciones por su ID.
	 * 
	 * @param id identificador único de la solicitud
	 * @return objeto vacaciones correspondiente al ID
	 */
	@Override
	public Vacation getVacation(String id) {
		log.debug("-> Obteniendo unas vacaciones <-");

		return vacationRepositoryOutput.findVacationById(id);
	}

	/**
	 * Recupera todas las vacaciones solicitadas por un empleado específico.
	 * 
	 * @param id ID del empleado
	 * @return lista de vacaciones asociados a un empleado
	 */
	@Override
	public List<Vacation> getEmployeeVacation(String id) {
		log.debug("-> Obteniendo las vacaciones de un empleado <-");

		return vacationRepositoryOutput.findVacationByEmployeeId(id);
	}

	/**
	 * Recupera todas las vacaciones solicitadas en un departamento específico.
	 * 
	 * @param id ID del departamento
	 * @return lista de vacaciones asociadas al departamento
	 */
	@Override
	public List<Vacation> getDepartmentVacation(String id) {
		log.debug("-> Obteniendo las vacaciones de un departamento <-");

		return vacationRepositoryOutput.findVacationByDepartmentId(id);
	}

	/**
	 * Recupera una página de solicitudes de vacaciones de un empleado, con
	 * posibilidad de filtrar por estado.
	 * 
	 * @param employeeId ID del empleado
	 * @param page       número de página (empezando desde 0)
	 * @param size       número de elementos por página
	 * @param status     estado de las vacaciones (opcional, puede ser null o vacío)
	 * @return página de vacaciones del empleado
	 */
	@Override
	public Page<Vacation> getPaginatedEmployeeVacations(String employeeId, int page, int size, String status) {
		Pageable pageable = PageRequest.of(page, size);

		if (status != null && !status.isBlank()) {
			return vacationRepositoryOutput.findByEmployeeIdAndStatus(employeeId, status, pageable);
		} else {
			return vacationRepositoryOutput.findByEmployeeId(employeeId, pageable);
		}
	}

	/**
	 * Recupera una página de solicitudes de vacaciones de un departamento, con
	 * posibilidad de filtrar por estado.
	 * 
	 * @param departmentId ID del departamento
	 * @param page         número de página (empezando desde 0)
	 * @param size         número de elementos por página
	 * @param status       estado de las vacaciones (opcional, puede ser null o
	 *                     vacío)
	 * @return página de vacaciones del departamento
	 */
	@Override
	public Page<Vacation> getPaginatedDepartmentVacations(String departmentId, int page, int size, String status) {
		log.debug("-> Obteniendo vacaciones paginadas del departamento {} <-", departmentId);

		Pageable pageable = PageRequest.of(page, size);

		if (status != null && !status.isBlank()) {
			return vacationRepositoryOutput.findByDepartmentIdAndStatus(departmentId, status, pageable);
		} else {
			return vacationRepositoryOutput.findByDepartmentId(departmentId, pageable);
		}
	}
}