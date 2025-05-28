package com.iem.tfm.infrastructure.database.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.domain.model.Vacation;
import com.iem.tfm.infrastructure.database.entity.VacationEntity;

/**
 * 
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VacationEntityMapper {

	/**
	 * 
	 * @param vacation
	 * @return
	 */
	public VacationEntity toEntity(Vacation vacation);

	/**
	 * 
	 * @param entity
	 * @param departments
	 * @return
	 */
	public default Vacation toDomain(VacationEntity entity) {
		return new Vacation(entity.getId(), entity.getStartDate(), entity.getEndDate(), entity.getEmployeeId(),
				entity.getStatus());
	}

	/**
	 * 
	 * @param entities
	 * @param departments
	 * @return
	 */
	public default List<Vacation> toDomainList(List<VacationEntity> entities) {
		List<Vacation> vacations = new ArrayList<>();

		if (entities == null)
			return vacations;

		for (VacationEntity entity : entities) {
			Vacation vacation = toDomain(entity);
			vacations.add(vacation);
		}

		return vacations;
	}
}