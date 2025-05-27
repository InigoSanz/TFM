package com.iem.tfm.infrastructure.database.repository.adapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iem.tfm.application.port.output.DepartmentRepositoryOutputPort;
import com.iem.tfm.application.port.output.VacationRepositoryOutputPort;
import com.iem.tfm.domain.exception.VacationDomainException;
import com.iem.tfm.domain.model.Department;
import com.iem.tfm.domain.model.Vacation;
import com.iem.tfm.infrastructure.database.entity.VacationEntity;
import com.iem.tfm.infrastructure.database.mapper.VacationEntityMapper;
import com.iem.tfm.infrastructure.database.repository.VacationRepository;

/**
 * 
 */
@Repository
public class VacationRepositoryAdapter implements VacationRepositoryOutputPort {
	
	@Autowired
	VacationRepository vacationRepository;
	
	@Autowired
	VacationEntityMapper vacationEntityMapper;
	
	@Autowired
	DepartmentRepositoryOutputPort departmentRepositoryOutput;

	@Override
	public String save(Vacation vacation) {
		VacationEntity entity = vacationEntityMapper.toEntity(vacation);
		VacationEntity savedVacation = vacationRepository.save(entity);
		
		return savedVacation.getId();
	}

	@Override
	public List<Vacation> findByEmployeeIdAndDateOverlap(String employeeId, Date startDate, Date endDate) {
		List<VacationEntity> vacationOverlaps = vacationRepository.findByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(employeeId, endDate, startDate);
		
		
		List<Vacation> overlapResult = new ArrayList<>();
		List<Department> departments = departmentRepositoryOutput.findAll();
		for (VacationEntity entity : vacationOverlaps) {
			Vacation vacation = vacationEntityMapper.toDomain(entity, departments);
			overlapResult.add(vacation);
		}
		
		return overlapResult;
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
		
		VacationEntity entity = entityOptional.get();
		
		List<Department> departments = departmentRepositoryOutput.findAll();
		
		return vacationEntityMapper.toDomain(entity, departments);
	}

	@Override
	public List<Vacation> findVacationByEmployeeId(String id) {
		List<VacationEntity> entities = vacationRepository.findByEmployeeId(id);
		List<Department> departments = departmentRepositoryOutput.findAll();
		
		List<Vacation> result = new ArrayList<>();
		for (VacationEntity entity : entities) {
			result.add(vacationEntityMapper.toDomain(entity, departments));
		}
		
		return result;
	}
}