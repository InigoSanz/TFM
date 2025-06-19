package com.iem.tfm.infrastructure.apirest.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iem.tfm.application.port.input.EmployeeGetInputPort;
import com.iem.tfm.application.port.input.VacationGetInputPort;
import com.iem.tfm.application.port.input.VacationRegisterInputPort;
import com.iem.tfm.application.port.input.VacationStatusInputPort;
import com.iem.tfm.domain.command.VacationRegisterCommand;
import com.iem.tfm.domain.command.VacationStatusChangeCommand;
import com.iem.tfm.domain.model.Vacation;
import com.iem.tfm.infrastructure.apirest.dto.request.VacationRequestDto;
import com.iem.tfm.infrastructure.apirest.dto.request.VacationStatusChangeRequestDto;
import com.iem.tfm.infrastructure.apirest.dto.response.VacationResponseDto;
import com.iem.tfm.infrastructure.database.entity.VacationEntity;
import com.iem.tfm.infrastructure.database.mapper.VacationDtoMapper;
import com.iem.tfm.infrastructure.database.repository.VacationRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para operaciones relacionadas con vacaciones.
 * <p>
 * Expone endpoints para registrar nuevas solicitudes de vacaciones, consultar
 * vacaciones existentes (todas, por ID o por empleado), y cambiar su estado
 * (aprobación o rechazo).
 * </p>
 *
 * <p>
 * Realiza las conversiones entre DTOs y comandos usando
 * {@link VacationDtoMapper}.
 * </p>
 *
 * @author Inigo
 * @version 1.0
 */
@RestController
@RequestMapping("/vacations")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class VacationController {

	@Autowired
	VacationRegisterInputPort vacationRegisterInputPort;

	@Autowired
	VacationDtoMapper vacationDtoMapper;

	@Autowired
	VacationGetInputPort vacationGetInputPort;

	@Autowired
	VacationStatusInputPort vacationStatusInputPort;

	@Autowired
	EmployeeGetInputPort employeeGetInputPort;

	@Autowired
	VacationRepository vacationRepository;

	/**
	 * Registra una nueva solicitud de vacaciones.
	 *
	 * @param vacationDto DTO con los datos de la solicitud
	 * @return respuesta con código 201 Created y URI del nuevo recurso
	 */
	@PostMapping
	public ResponseEntity<Void> vacationRegister(@RequestBody VacationRequestDto vacationDto) {
		log.debug("-> Petición para registrar unas vacaciones recibida <-");

		VacationRegisterCommand registerCommand = vacationDtoMapper.fromDtoToCommand(vacationDto);

		String id = vacationRegisterInputPort.vacationRegister(registerCommand);

		log.debug("-> Vacaciones registradas exitosamente <-");

		return ResponseEntity.created(crearUri(id)).build();
	}

	/**
	 * Recupera todas las solicitudes de vacaciones registradas.
	 *
	 * @return lista de {@link VacationResponseDto}
	 */
	@GetMapping
	public ResponseEntity<List<VacationResponseDto>> getAllVacations() {
		log.debug("-> Petición para obtener todas las vacaciones recibida <-");

		List<VacationResponseDto> responseDtoList = vacationDtoMapper
				.fromDomainToDtoList(vacationGetInputPort.getAllVacation());

		if (responseDtoList.isEmpty()) {
			log.warn("-> No hay vacaciones registradas <-");
			return ResponseEntity.noContent().build();
		}

		log.debug("-> Vacaciones obtenidas exitosamente <-");

		return ResponseEntity.ok(responseDtoList);
	}

	/**
	 * Recupera una solicitud de vacaciones por su ID.
	 *
	 * @param id identificador de la solicitud
	 * @return objeto {@link VacationResponseDto}
	 */
	@GetMapping("/{vacation-id}")
	public ResponseEntity<VacationResponseDto> getVacation(@PathVariable("vacation-id") String id) {
		log.debug("-> Petición para obtener unas vacaciones por ID recibida <-");

		Vacation vacation = vacationGetInputPort.getVacation(id);

		if (vacation == null) {
			log.warn("-> Vacaciones con ID [{}] no encontradas <-", id);
			return ResponseEntity.notFound().build();
		}

		String employeeName = employeeGetInputPort.getEmployee(vacation.getEmployeeId()).getName();

		String resolvedBy = null;
		if (vacationRepository.findById(vacation.getId()).isPresent()) {
			resolvedBy = vacationRepository.findById(vacation.getId()).get().getResolvedBy();
		}

		VacationResponseDto vacationDto = vacationDtoMapper.fromDomaintoDto(vacation, employeeName, resolvedBy);

		log.debug("-> Vacaciones obtenidas exitosamente <-");

		return ResponseEntity.ok(vacationDto);
	}

	/**
	 * Recupera todas las vacaciones asociadas a un empleado.
	 *
	 * @param id ID del empleado
	 * @return lista de {@link VacationResponseDto}
	 */
	@GetMapping("/employee/{employee-id}")
	public ResponseEntity<List<VacationResponseDto>> getVacationsOfEmployee(@PathVariable("employee-id") String id) {
		log.debug("-> Petición para obtener vacaciones del empleado con id: " + id + " recibida <-");

		List<VacationResponseDto> responseDtoList = vacationDtoMapper
				.fromDomainToDtoList(vacationGetInputPort.getEmployeeVacation(id));

		if (responseDtoList.isEmpty()) {
			log.warn("-> El empleado no tiene vacaciones registradas <-");
			return ResponseEntity.noContent().build();
		}

		log.debug("-> Vacaciones del empleado obtenidas exitosamente <-");

		return ResponseEntity.ok(responseDtoList);
	}

	/**
	 * Cambia el estado de una solicitud de vacaciones.
	 *
	 * @param id  ID de la solicitud de vacaciones
	 * @param dto DTO con los datos del cambio de estado
	 * @return respuesta con estado 200 OK
	 */
	@PatchMapping("/{vacation-id}/status")
	public ResponseEntity<Void> vacationStatusChange(@PathVariable("vacation-id") String id,
			@RequestBody VacationStatusChangeRequestDto dto) {
		log.debug("-> Petición para cambiar el estado de las vacaciones recibida <-");

		VacationStatusChangeCommand command = new VacationStatusChangeCommand(id, dto.getEmployeeId(), dto.getRole(),
				dto.isApprove(), dto.getResolvedByName());

		vacationStatusInputPort.statusChange(command);

		log.debug("-> Estado de las vacaciones actualizado exitosamente <-");

		return ResponseEntity.ok().build();
	}

	@GetMapping("/department/{department-id}")
	public ResponseEntity<List<VacationResponseDto>> getVacationsOfDepartment(
			@PathVariable("department-id") String id) {
		log.debug("-> Petición para obtener vacaciones del departamento con id: " + id + " recibida <-");

		List<Vacation> vacations = vacationGetInputPort.getDepartmentVacation(id);

		if (vacations.isEmpty()) {
			log.warn("-> No hay vacaciones en este departamento <-");
			return ResponseEntity.noContent().build();
		}

		List<VacationResponseDto> responseDtoList = new ArrayList<>();

		for (Vacation vac : vacations) {
			String employeeName = employeeGetInputPort.getEmployee(vac.getEmployeeId()).getName();

			String resolvedBy = null;
			if (vacationRepository.findById(vac.getId()).isPresent()) {
				resolvedBy = vacationRepository.findById(vac.getId()).get().getResolvedBy();
			}

			VacationResponseDto dto = vacationDtoMapper.fromDomaintoDto(vac, employeeName, resolvedBy);
			responseDtoList.add(dto);
		}

		log.debug("-> Vacaciones del departamento obtenidas exitosamente <-");

		return ResponseEntity.ok(responseDtoList);
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("employee/{employee-id}/paginated")
	public ResponseEntity<Page<VacationResponseDto>> getPaginatedVacationsEmployee(
			@PathVariable("employee-id") String employeeId, @RequestParam int page, @RequestParam int size,
			@RequestParam(required = false) String status) {
		log.debug("-> Petición paginada para vacaciones del empleado con id: " + employeeId + " <-");

		Page<Vacation> vacationPage = vacationGetInputPort.getPaginatedEmployeeVacations(employeeId, page, size,
				status);

		List<VacationResponseDto> dtoList = new ArrayList<>();

		for (Vacation vacation : vacationPage.getContent()) {
			String employeeIdVacation = vacation.getEmployeeId();
			String employeeName = employeeGetInputPort.getEmployee(employeeIdVacation).getName();

			String resolvedBy = vacationRepository.findById(employeeIdVacation).map(VacationEntity::getResolvedBy)
					.orElse(null);

			VacationResponseDto dto = vacationDtoMapper.fromDomaintoDto(vacation, employeeName, resolvedBy);
			dtoList.add(dto);
		}

		Page<VacationResponseDto> responsePage = new PageImpl<>(dtoList, PageRequest.of(page, size),
				vacationPage.getTotalElements());

		log.debug("-> Petición paginada para vacaciones del empleado obtenida exitosamente <-");
		
		 return ResponseEntity.ok(responsePage);
	}

	/**
	 * Crea la URI para el nuevo recurso creado.
	 *
	 * @param id identificador del recurso
	 * @return URI del nuevo recurso
	 */
	public static URI crearUri(String id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}