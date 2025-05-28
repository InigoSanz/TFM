package com.iem.tfm.infrastructure.database.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.application.command.VacationRegisterCommand;
import com.iem.tfm.domain.model.Vacation;
import com.iem.tfm.infrastructure.apirest.dto.request.VacationRequestDto;
import com.iem.tfm.infrastructure.apirest.dto.response.VacationResponseDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VacationDtoMapper {

	public VacationRegisterCommand fromDtoToCommand(VacationRequestDto dto);

	public default VacationResponseDto fromDomaintoDto(Vacation vacation) {
		return new VacationResponseDto(vacation.getId(), vacation.getStartDate(), vacation.getEndDate(),
				vacation.getEmployeeId(), vacation.getStatus());
	}

	public List<VacationResponseDto> fromDomainToDtoList(List<Vacation> allVacation);
}