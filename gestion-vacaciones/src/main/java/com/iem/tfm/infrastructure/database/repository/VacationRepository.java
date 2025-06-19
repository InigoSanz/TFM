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
	List<VacationEntity> findByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(String employeeId,
			Date endDate, Date startDate);

	/**
	 * Recupera todas las vacaciones asociadas a un empleado específico.
	 * 
	 * @param employeeId ID del empleado
	 * @return lista de solicitudes de vacaciones
	 */
	List<VacationEntity> findByEmployeeId(String employeeId);

	/**
	 * 
	 * @param id
	 * @return
	 */
	List<VacationEntity> findByDepartmentIdsContaining(String departmentId);
	
	/**
	 * 
	 * @param employeeId
	 * @param status
	 * @param pageable
	 * @return
	 */
	Page<VacationEntity> findByEmployeeIdAndStatus(String employeeId, String status, Pageable pageable);
	
	/**
	 * 
	 * @param employeeId
	 * @param pageable
	 * @return
	 */
	Page<VacationEntity> findByEmployeeId(String employeeId, Pageable pageable);
}