package com.iem.tfm.infrastructure.database.adapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.iem.tfm.application.port.output.VacationRepositoryOutputPort;
import com.iem.tfm.domain.exception.VacationDomainException;
import com.iem.tfm.domain.model.Vacation;
import com.iem.tfm.infrastructure.database.entity.VacationEntity;
import com.iem.tfm.infrastructure.database.mapper.VacationEntityMapper;
import com.iem.tfm.infrastructure.database.repository.VacationRepository;

/**
 * Adaptador de infraestructura que implementa el puerto de salida
 * {@link VacationRepositoryOutputPort}.
 * <p>
 * Encapsula el acceso a la base de datos utilizando el repositorio Spring Data
 * {@link VacationRepository} y transforma las entidades {@link VacationEntity}
 * hacia y desde el modelo de dominio {@link Vacation} mediante el mapper
 * {@link VacationEntityMapper}.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Component
public class VacationRepositoryAdapter implements VacationRepositoryOutputPort {

	@Autowired
	VacationRepository vacationRepository;

	@Autowired
	VacationEntityMapper vacationEntityMapper;

	/**
	 * Guarda una solicitud de vacaciones en la base de datos.
	 * 
	 * @param vacation objeto del modelo de dominio a persistir
	 * @return ID generado para la solicitud guardada
	 */
	@Override
	public String save(Vacation vacation) {
		VacationEntity entity = vacationEntityMapper.toEntity(vacation);
		VacationEntity savedVacation = vacationRepository.save(entity);

		return savedVacation.getId();
	}

	/**
	 * Recupera las solicitudes de vacaciones de un empleado que se solapen con un
	 * rango de fechas.
	 * 
	 * @param employeeId ID del empleado
	 * @param startDate  fecha de inicio del nuevo rango
	 * @param endDate    fecha de fin del nuevo rango
	 * @return lista de solicitudes de vacaciones solapadas
	 */
	@Override
	public List<Vacation> findByEmployeeIdAndDateOverlap(String employeeId, Date startDate, Date endDate) {
		List<VacationEntity> vacationOverlaps = vacationRepository
				.findByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(employeeId, endDate, startDate);

		return vacationEntityMapper.toDomainList(vacationOverlaps);
	}

	/**
	 * Recupera todas las solicitudes de vacaciones registradas en la base de datos.
	 * 
	 * @return lista de objetos del modelo de dominio
	 */
	@Override
	public List<Vacation> findAll() {
		List<VacationEntity> entities = vacationRepository.findAll();

		return vacationEntityMapper.toDomainList(entities);
	}

	/**
	 * Busca una solicitud de vacaciones por su ID.
	 * 
	 * @param id identificador de la solicitud
	 * @return objeto Vacation correspondiente
	 * @throws VacationDomainException si no se encuentra ninguna solicitud con ese
	 *                                 ID
	 */
	@Override
	public Vacation findVacationById(String id) {
		Optional<VacationEntity> entityOptional = vacationRepository.findById(id);

		if (!entityOptional.isPresent()) {
			throw new VacationDomainException("Vacaciones no encontradas con id: " + id);
		}

		return vacationEntityMapper.toDomain(entityOptional.get());
	}

	/**
	 * Recupera todas las solicitudes de vacaciones asociadas a un empleado
	 * específico.
	 * 
	 * @param id ID del empleado
	 * @return lista de objetos Vacation del empleado
	 */
	@Override
	public List<Vacation> findVacationByEmployeeId(String id) {
		List<VacationEntity> entities = vacationRepository.findByEmployeeId(id);

		return vacationEntityMapper.toDomainList(entities);
	}

	/**
	 * 
	 */
	@Override
	public List<Vacation> findVacationByDepartmentId(String id) {
		List<VacationEntity> entities = vacationRepository.findByDepartmentIdsContaining(id);
		return vacationEntityMapper.toDomainList(entities);
	}

	/**
	 * 
	 */
	@Override
	public void setResolvedBy(String vacationId, String resolvedBy) {
		Optional<VacationEntity> entityOptional = vacationRepository.findById(vacationId);

		if (!entityOptional.isPresent()) {
			throw new VacationDomainException("Vacaciones no encontradas con id: " + vacationId);
		}

		VacationEntity entity = entityOptional.get();
		entity.setResolvedBy(resolvedBy);
		vacationRepository.save(entity);
	}

	/**
	 * 
	 */
	@Override
	public Page<Vacation> findByEmployeeIdAndStatus(String employeeId, String status, Pageable pageable) {
		Page<VacationEntity> entityPage = vacationRepository.findByEmployeeIdAndStatus(employeeId, status, pageable);

		List<Vacation> domainList = new ArrayList<>();
		for (VacationEntity entity : entityPage.getContent()) {
			domainList.add(vacationEntityMapper.fromEntityToDomain(entity));
		}

		return new PageImpl<>(domainList, pageable, entityPage.getTotalElements());
	}

	/**
	 * 
	 */
	@Override
	public Page<Vacation> findByEmployeeId(String employeeId, Pageable pageable) {
		Page<VacationEntity> entityPage = vacationRepository.findByEmployeeId(employeeId, pageable);

		List<Vacation> domainList = new ArrayList<>();
		for (VacationEntity entity : entityPage.getContent()) {
			domainList.add(vacationEntityMapper.fromEntityToDomain(entity));
		}

		return new PageImpl<>(domainList, pageable, entityPage.getTotalElements());
	}
}