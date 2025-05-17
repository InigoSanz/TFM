package com.iem.tfm.infrastructure.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iem.tfm.application.port.input.DepartmentGetInputPort;
import com.iem.tfm.infrastructure.apirest.dto.response.DepartmentResponseDto;
import com.iem.tfm.infrastructure.database.mapper.DepartmentDtoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para los endpoints de los departamentos.
 * 
 * @author Inigo
 * @version 1.0
 */
@RestController
@RequestMapping("/department")
@Slf4j
public class DepartmentController {

	@Autowired
	DepartmentGetInputPort departmentGetInput;

	@Autowired
	DepartmentDtoMapper departmentDtoMapper;

	@GetMapping
	public ResponseEntity<List<DepartmentResponseDto>> getDepartments() {
		log.debug("-> Petición para obtener todos los departamentos recibida <-");

		List<DepartmentResponseDto> responseDtoList = departmentDtoMapper.toDtoList(departmentGetInput.getAllDepartment());

		log.debug("-> Departamentos obtenidos exitosamente <-");
		
		return ResponseEntity.ok(responseDtoList);
	}

	@GetMapping("/{department-id}")
	public ResponseEntity<DepartmentResponseDto> getDepartment(@PathVariable("department-id") String id) {
		log.debug("-> Petición para obtener un departamento por ID recibida <-");
		
		DepartmentResponseDto departmentDto = departmentDtoMapper.toDto(departmentGetInput.getDepartment(id));
		
		log.debug("-> Departamento obtenido exitosamente <-");
		
		return ResponseEntity.ok(departmentDto);
	}
}