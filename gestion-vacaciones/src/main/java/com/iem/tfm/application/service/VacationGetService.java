package com.iem.tfm.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.port.input.VacationGetInputPort;
import com.iem.tfm.application.port.output.VacationRepositoryOutputPort;
import com.iem.tfm.domain.model.Vacation;

import lombok.extern.slf4j.Slf4j;

/**
 * Servicio de aplicación para consultar solicitudes de vacaciones.
 * <p>
 * Implementa el caso de uso definido en {@link VacationGetInputPort} y accede
 * a los datos mediante el puerto de salida {@link VacationRepositoryOutputPort}.
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
		log.info("-> Obteniendo todas las vacaciones <-");
		
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
		log.info("-> Obteniendo unas vacaciones <-");
		
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
		log.info("-> Obteniendo las vacaciones de un empleado <-");
		
		return vacationRepositoryOutput.findVacationByEmployeeId(id);
	}
	
	/**
	 * 
	 */
	@Override
	public List<Vacation> getDepartmentVacation(String id) {
		log.info("-> Obteniendo las vacaciones de un departamento <-");
		
		return vacationRepositoryOutput.findVacationByDepartmentId(id);
	}
}