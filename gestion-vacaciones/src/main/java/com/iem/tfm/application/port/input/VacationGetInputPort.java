package com.iem.tfm.application.port.input;

import java.util.List;

import com.iem.tfm.domain.model.Vacation;

/**
 * Puerto de entrada para el caso de uso de consulta de vacaciones.
 * <p>
 * Define las operaciones necesarias para recuperar información sobre las
 * solicitudes de vacaciones, tanto a nivel global como por empleado específico.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public interface VacationGetInputPort {

	/**
	 * Recupera todas las solicitudes de vacaciones registradas.
	 * 
	 * @return lista de las vacaciones
	 */
	public List<Vacation> getAllVacation();

	/**
	 * Recupera una solicitud de vacaciones por su ID.
	 * 
	 * @param id ID de la solicitud de vacaciones
	 * @return Vacaciones correspondiente al ID
	 */
	public Vacation getVacation(String id);

	/**
	 * Recupera todas las solicitudes de vacaciones asociadas a un empleado.
	 * 
	 * @param id ID del empleado
	 * @return lista de vacaciones de un empleado
	 */
	public List<Vacation> getEmployeeVacation(String id);

	public List<Vacation> getDepartmentVacation(String id);
}