package com.iem.tfm.infrastructure.apirest.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iem.tfm.application.command.VacationRegisterCommand;
import com.iem.tfm.application.port.input.VacationRegisterInputPort;
import com.iem.tfm.infrastructure.apirest.dto.request.VacationRequestDto;
import com.iem.tfm.infrastructure.database.mapper.VacationDtoMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/vacations")
@Slf4j
public class VacationController {
	
	@Autowired
	VacationRegisterInputPort vacationRegisterInputPort;
	
	@Autowired
	VacationDtoMapper vacationDtoMapper;
	
	@PostMapping
	public ResponseEntity<Void> vacationRegister(@RequestBody VacationRequestDto vacationDto) {
		log.debug("-> Petición para registrar unas vacaciones recibida <-");
		
		VacationRegisterCommand registerCommand = vacationDtoMapper.fromDtoToCommand(vacationDto);
		
		String id = vacationRegisterInputPort.vacationRegister(registerCommand);
		
		log.debug("-> Vacaciones registradas exitosamente <-");
		
		return ResponseEntity.created(crearUri(id)).build();
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