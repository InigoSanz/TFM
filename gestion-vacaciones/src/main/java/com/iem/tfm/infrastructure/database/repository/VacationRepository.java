package com.iem.tfm.infrastructure.database.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.iem.tfm.infrastructure.database.entity.VacationEntity;

/**
 * Repositorio MongoDB para la colección de vacaciones.
 * <p>
 * Extiende {@link MongoRepository} para proporcionar operaciones CRUD y
 * consultas personalizadas sobre documentos de tipo {@link VacationEntity}.
 * </p>
 * 
 * <p>
 * <b>Nota:</b> A partir de una necesidad detectada en la capa de aplicación, se
 * ha incluido una consulta personalizada para detectar solapamientos de fechas,
 * usando el método
 * {@code findByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual}.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Repository
@EnableMongoRepositories
public interface VacationRepository extends MongoRepository<VacationEntity, String> {

	/**
	 * Busca todas las vacaciones de un empleado que se solapen con un rango de
	 * fechas dado.
	 * 
	 * @param employeeId ID del empleado
	 * @param endDate    fecha de fin de la nueva solicitud
	 * @param startDate  fecha de inicio de la nueva solicitud
	 * @return lista de vacaciones solapadas
	 */
	public List<VacationEntity> findByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(String employeeId,
			Date endDate, Date startDate);

	/**
	 * Recupera todas las vacaciones asociadas a un empleado específico.
	 * 
	 * @param employeeId ID del empleado
	 * @return lista de solicitudes de vacaciones
	 */
	public List<VacationEntity> findByEmployeeId(String employeeId);

	/**
	 * Recupera todas las solicitudes de vacaciones asociadas a un departamento
	 * concreto.
	 * 
	 * @param departmentId ID del departamento
	 * @return lista de vacaciones asociadas a ese departamento
	 */
	public List<VacationEntity> findByDepartmentIdsContaining(String departmentId);

	/**
	 * Recupera una página de vacaciones filtradas por empleado y estado.
	 * 
	 * @param employeeId ID del empleado
	 * @param status     estado de la solicitud (pendiente, aprobada, etc.)
	 * @param pageable   configuración de paginación
	 * @return página de vacaciones coincidentes
	 */
	public Page<VacationEntity> findByEmployeeIdAndStatus(String employeeId, String status, Pageable pageable);

	/**
	 * Recupera una página de vacaciones de un empleado sin filtrar por estado.
	 * 
	 * @param employeeId ID del empleado
	 * @param pageable   configuración de paginación
	 * @return página de vacaciones del empleado
	 */
	public Page<VacationEntity> findByEmployeeId(String employeeId, Pageable pageable);

	/**
	 * Recupera una página de vacaciones asociadas a un departamento.
	 * 
	 * @param departmentId ID del departamento
	 * @param pageable     configuración de paginación
	 * @return página de vacaciones del departamento
	 */
	public Page<VacationEntity> findByDepartmentIdsContaining(String departmentId, Pageable pageable);

	/**
	 * Recupera una página de vacaciones de un departamento, filtradas por estado.
	 * 
	 * @param departmentId ID del departamento
	 * @param status       estado de la solicitud
	 * @param pageable     configuración de paginación
	 * @return página de vacaciones filtradas por estado y departamento
	 */
	public Page<VacationEntity> findByDepartmentIdsContainingAndStatus(String departmentId, String status,
			Pageable pageable);
}