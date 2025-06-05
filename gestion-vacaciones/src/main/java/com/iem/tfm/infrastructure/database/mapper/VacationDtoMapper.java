package com.iem.tfm.infrastructure.database.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.application.command.VacationRegisterCommand;
import com.iem.tfm.domain.model.Vacation;
import com.iem.tfm.infrastructure.apirest.dto.request.VacationRequestDto;
import com.iem.tfm.infrastructure.apirest.dto.response.VacationResponseDto;

/**
 * Mapper para convertir entre DTOs relacionados con vacaciones y el modelo de dominio o comandos.
 * <p>
 * Esta interfaz facilita la transformación entre objetos expuestos por la API REST
 * ({@link VacationRequestDto}, {@link VacationResponseDto}) y los objetos de dominio
 * ({@link Vacation}) o comandos ({@link VacationRegisterCommand}).
 * </p>
 * 
 * Usa MapStruct para el mapeo automático de DTO a comando, y métodos `default` para
 * conversiones manuales de dominio a DTO.
 * 
 * @author Inigo
 * @version 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VacationDtoMapper {
	
	/**
	 * Convierte un VacationRequestDto recibido desde la API en un VacationRegisterCommand
	 * para ser procesado por la capa de aplicación.
	 * 
	 * @param dto objeto de entrada recibido vía API
	 * @return comando correspondiente para registrar una solicitud de vacaciones
	 */
	public VacationRegisterCommand fromDtoToCommand(VacationRequestDto dto);
	
	/**
	 * Convierte un objeto del dominio Vacation en un DTO de respuesta VacationResponseDto.
	 * 
	 * @param vacation objeto del modelo de dominio
	 * @return objeto DTO listo para ser enviado como respuesta al cliente
	 */
	public default VacationResponseDto fromDomaintoDto(Vacation vacation) {
		return new VacationResponseDto(vacation.getId(), vacation.getStartDate(), vacation.getEndDate(),
				vacation.getEmployeeId(), vacation.getStatus(), vacation.getDepartmentIds());
	}
	
	/**
	 * Convierte una lista de objetos del dominio Vacation en una lista de DTOs de respuesta.
	 * 
	 * @param allVacation lista de vacaciones del modelo de dominio
	 * @return lista de VacationResponseDto
	 */
	public List<VacationResponseDto> fromDomainToDtoList(List<Vacation> allVacation);
}