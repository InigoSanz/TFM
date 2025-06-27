package com.iem.tfm.application.port.output;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iem.tfm.domain.model.Vacation;

/**
 * Puerto de salida para operaciones de persistencia y consulta de
 * {@link Vacation}.
 * <p>
 * Define las acciones que la capa de aplicación necesita realizar sobre el
 * repositorio de solicitudes de vacaciones. Será implementado por un adaptador
 * en la capa de infraestructura.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public interface VacationRepositoryOutputPort {

	/**
	 * Guarda una solicitud de vacaciones en el sistema.
	 * 
	 * @param vacation objeto a persistir
	 * @return ID generado de la solicitud guardada
	 */
	public String save(Vacation vacation);

	/**
	 * Busca las solicitudes de vacaciones de un empleado que se solapen con un
	 * rango de fechas.
	 * 
	 * @param employeeId ID del empleado
	 * @param startDate  fecha de inicio propuesta
	 * @param endDate    fecha de fin propuesta
	 * @return lista de vacaciones que se solapan con las fechas indicadas
	 */
	public List<Vacation> findByEmployeeIdAndDateOverlap(String employeeId, Date startDate, Date endDate);

	/**
	 * Recupera todas las solicitudes de vacaciones registradas en el sistema.
	 * 
	 * @return lista de vacaciones
	 */
	public List<Vacation> findAll();

	/**
	 * Recupera una solicitud de vacaciones a partir de su identificador único.
	 * 
	 * @param id ID de la solicitud
	 * @return vacaciones correspondiente al ID
	 */
	public Vacation findVacationById(String id);

	/**
	 * Recupera todas las solicitudes de vacaciones asociadas a un empleado.
	 * 
	 * @param id ID del empleado
	 * @return lista de vacaciones de un empleado
	 */
	public List<Vacation> findVacationByEmployeeId(String id);

	/**
	 * Recupera todas las solicitudes de vacaciones asociadas a un departamento.
	 * 
	 * @param id ID del departamento
	 * @return lista de vacaciones del departamento
	 */
	public List<Vacation> findVacationByDepartmentId(String id);

	/**
	 * Marca una solicitud de vacaciones como resuelta por un usuario específico.
	 * 
	 * @param vacationId ID de la solicitud de vacaciones
	 * @param resolvedBy ID del usuario que resolvió la solicitud
	 */
	public void setResolvedBy(String vacationId, String resolvedBy);

	/**
	 * Recupera una página de solicitudes de vacaciones de un empleado con un estado
	 * específico.
	 * 
	 * @param employeeId ID del empleado
	 * @param status     estado de la solicitud (por ejemplo: PENDING, APPROVED,
	 *                   REJECTED)
	 * @param pageable   información de paginación
	 * @return página de vacaciones del empleado filtradas por estado
	 */
	public Page<Vacation> findByEmployeeIdAndStatus(String employeeId, String status, Pageable pageable);

	/**
	 * Recupera una página de solicitudes de vacaciones de un empleado, sin filtrar
	 * por estado.
	 * 
	 * @param employeeId ID del empleado
	 * @param pageable   información de paginación
	 * @return página de vacaciones del empleado
	 */
	public Page<Vacation> findByEmployeeId(String employeeId, Pageable pageable);

	/**
	 * Recupera una página de solicitudes de vacaciones de un departamento con un
	 * estado específico.
	 * 
	 * @param departmentId ID del departamento
	 * @param status       estado de la solicitud
	 * @param pageable     información de paginación
	 * @return página de vacaciones del departamento filtradas por estado
	 */
	public Page<Vacation> findByDepartmentIdAndStatus(String departmentId, String status, Pageable pageable);

	/**
	 * Recupera una página de solicitudes de vacaciones de un departamento, sin
	 * filtrar por estado.
	 * 
	 * @param departmentId ID del departamento
	 * @param pageable     información de paginación
	 * @return página de vacaciones del departamento
	 */
	public Page<Vacation> findByDepartmentId(String departmentId, Pageable pageable);
}