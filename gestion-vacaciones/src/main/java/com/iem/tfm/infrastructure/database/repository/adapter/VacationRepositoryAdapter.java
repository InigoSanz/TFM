package com.iem.tfm.infrastructure.database.repository.adapter;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iem.tfm.application.port.output.VacationRepositoryOutputPort;
import com.iem.tfm.domain.exception.VacationDomainException;
import com.iem.tfm.domain.model.Vacation;
import com.iem.tfm.infrastructure.database.entity.VacationEntity;
import com.iem.tfm.infrastructure.database.mapper.VacationEntityMapper;
import com.iem.tfm.infrastructure.database.repository.VacationRepository;

/**
 * 
 */
@Component
public class VacationRepositoryAdapter implements VacationRepositoryOutputPort {

	@Autowired
	VacationRepository vacationRepository;

	@Autowired
	VacationEntityMapper vacationEntityMapper;

	@Override
	public String save(Vacation vacation) {
		VacationEntity entity = vacationEntityMapper.toEntity(vacation);
		VacationEntity savedVacation = vacationRepository.save(entity);

		return savedVacation.getId();
	}

	@Override
	public List<Vacation> findByEmployeeIdAndDateOverlap(String employeeId, Date startDate, Date endDate) {
		List<VacationEntity> vacationOverlaps = vacationRepository
				.findByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(employeeId, endDate, startDate);

		return vacationEntityMapper.toDomainList(vacationOverlaps);
	}

	@Override
	public List<Vacation> findAll() {
		List<VacationEntity> entities = vacationRepository.findAll();

		return vacationEntityMapper.toDomainList(entities);
	}

	@Override
	public Vacation findVacationById(String id) {
		Optional<VacationEntity> entityOptional = vacationRepository.findById(id);

		if (!entityOptional.isPresent()) {
			throw new VacationDomainException("Vacaciones no encontradas con id: " + id);
		}

		return vacationEntityMapper.toDomain(entityOptional.get());
	}

	@Override
	public List<Vacation> findVacationByEmployeeId(String id) {
		List<VacationEntity> entities = vacationRepository.findByEmployeeId(id);
		
		return vacationEntityMapper.toDomainList(entities);
	}
}