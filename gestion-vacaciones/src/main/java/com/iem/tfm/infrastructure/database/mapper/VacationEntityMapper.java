package com.iem.tfm.infrastructure.database.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.domain.model.Vacation;
import com.iem.tfm.infrastructure.database.entity.VacationEntity;

/**
 * Mapper para convertir entre {@link Vacation} (modelo de dominio) y
 * {@link VacationEntity} (entidad de base de datos).
 * <p>
 * Utiliza MapStruct para mapear automáticamente del dominio a entidad. Los
 * métodos de entidad a dominio se implementan manualmente como {@code default}.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VacationEntityMapper {

	/**
	 * Convierte un objeto Vacation del dominio en su representación como
	 * VacationEntity.
	 * 
	 * @param vacation objeto del dominio a convertir
	 * @return entidad de base de datos equivalente
	 */
	public VacationEntity toEntity(Vacation vacation);

	/**
	 * Convierte una entidad VacationEntity a un objeto del modelo de dominio
	 * Vacation.
	 * 
	 * @param entity entidad persistida
	 * @return objeto del dominio correspondiente
	 */
	public default Vacation toDomain(VacationEntity entity) {
		return new Vacation(entity.getId(), entity.getStartDate(), entity.getEndDate(), entity.getEmployeeId(),
				entity.getStatus(), entity.getDepartmentIds(), entity.getResolvedBy());
	}

	/**
	 * Convierte una lista de entidades VacationEntity a una lista de objetos del
	 * dominio Vacation.
	 * 
	 * @param entities lista de entidades
	 * @return lista de objetos del dominio
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
	
	/**
	 * Utilizada en paginación
	 * 
	 * @param entity
	 * @return
	 */
	public Vacation fromEntityToDomain(VacationEntity entity);
}