package com.iem.tfm.infrastructure.apirest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iem.tfm.application.command.VacationRegisterCommand;
import com.iem.tfm.application.command.VacationStatusChangeCommand;
import com.iem.tfm.application.port.input.VacationGetInputPort;
import com.iem.tfm.application.port.input.VacationRegisterInputPort;
import com.iem.tfm.application.port.input.VacationStatusInputPort;
import com.iem.tfm.infrastructure.apirest.dto.request.VacationRequestDto;
import com.iem.tfm.infrastructure.apirest.dto.request.VacationStatusChangeRequestDto;
import com.iem.tfm.infrastructure.apirest.dto.response.VacationResponseDto;
import com.iem.tfm.infrastructure.database.mapper.VacationDtoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
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
	
	/**
	 * 
	 * @param vacationDto
	 * @return
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
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<VacationResponseDto>> getAllVacations() {
		log.debug("-> Petición para obtener todas las vacaciones recibida <-");
		
		List<VacationResponseDto> responseDtoList = vacationDtoMapper.fromDomainToDtoList(vacationGetInputPort.getAllVacation());;
	
		log.debug("-> Vacaciones obtenidas exitosamente <-");
		
		return ResponseEntity.ok(responseDtoList);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{vacation-id}")
	public ResponseEntity<VacationResponseDto> getVacation(@PathVariable("vacation-id") String id) {
		log.debug("-> Petición para obtener unas vacaciones por ID recibida <-");
	
		VacationResponseDto vacationDto = vacationDtoMapper.fromDomaintoDto(vacationGetInputPort.getVacation(id));
	
		log.debug("-> Vacaciones obtenidas exitosamente <-");
		
		return ResponseEntity.ok(vacationDto);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/employee/{employee-id}")
	public ResponseEntity<List<VacationResponseDto>> getVacationsOfEmployee(@PathVariable("employee-id") String id) {
		log.debug("-> Petición para obtener vacaciones del empleado con id: " + id + " recibida <-");
		
		List<VacationResponseDto> responseDtoList = vacationDtoMapper.fromDomainToDtoList(vacationGetInputPort.getEmployeeVacation(id));
	
		log.debug("-> Vacaciones del empleado obtenidas exitosamente <-");
		
		return ResponseEntity.ok(responseDtoList);
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
	
	@PatchMapping("/{vacation-id}/status")
	public ResponseEntity<Void> vacationStatusChange(@PathVariable("vacation-id") String id, @RequestBody VacationStatusChangeRequestDto dto) {
		log.debug("-> Petición para cambiar el estado de las vacaciones recibida <-");
		
		VacationStatusChangeCommand command = new VacationStatusChangeCommand(id, dto.getEmployeeId(), dto.getRole(), dto.isApprove());
	
		vacationStatusInputPort.statusChange(command);
		
		log.debug("-> Estado de las vacaciones actualizado exitosamente <-");
		
		return ResponseEntity.ok().build();
	}
}