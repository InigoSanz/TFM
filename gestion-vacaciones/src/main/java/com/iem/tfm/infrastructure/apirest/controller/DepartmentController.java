package com.iem.tfm.infrastructure.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iem.tfm.application.port.output.DepartmentRepositoryOutputPort;
import com.iem.tfm.infrastructure.apirest.dto.response.DepartmentResponseDto;
import com.iem.tfm.infrastructure.database.mapper.DepartmentDtoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para los endpoints de los empleados.
 * 
 * @author Inigo
 * @version 1.0
 */
@RestController
@RequestMapping("/department")
@Slf4j
public class DepartmentController {

	@Autowired
	DepartmentRepositoryOutputPort departmentRepositoryOutput;

	@Autowired
	DepartmentDtoMapper departmentDtoMapper;

	@GetMapping
	public ResponseEntity<List<DepartmentResponseDto>> getDepartments() {
		log.debug("-> Petición para obtener todos los departamentos recibida <-");

		List<DepartmentResponseDto> responseDtoList = departmentDtoMapper.toDtoList(departmentRepositoryOutput.findAll());

		log.debug("-> Departamentos obtenidos exitosamente <-");
		
		return ResponseEntity.ok(responseDtoList);
	}

	// TODO: Consultar un departamento por ID
}