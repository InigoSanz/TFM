package com.iem.tfm.application.port.input;

import java.util.List;

import org.springframework.data.domain.Page;

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

	/**
	 * Recupera todas las solicitudes de vacaciones asociadas a un departamento.
	 * 
	 * @param id ID del departamento
	 * @return lista de vacaciones del departamento
	 */
	public List<Vacation> getDepartmentVacation(String id);

	/**
	 * Recupera una página de solicitudes de vacaciones de un empleado, filtradas
	 * opcionalmente por estado.
	 * 
	 * @param employeeId ID del empleado
	 * @param page       número de página (empezando desde 0)
	 * @param size       número de elementos por página
	 * @param status     estado de las vacaciones (puede ser null para no filtrar)
	 * @return página de vacaciones del empleado
	 */
	public Page<Vacation> getPaginatedEmployeeVacations(String employeeId, int page, int size, String status);

	/**
	 * Recupera una página de solicitudes de vacaciones de un departamento,
	 * filtradas opcionalmente por estado.
	 * 
	 * @param departmentId ID del departamento
	 * @param page         número de página (empezando desde 0)
	 * @param size         número de elementos por página
	 * @param status       estado de las vacaciones (puede ser null para no filtrar)
	 * @return página de vacaciones del departamento
	 */
	public Page<Vacation> getPaginatedDepartmentVacations(String departmentId, int page, int size, String status);
}