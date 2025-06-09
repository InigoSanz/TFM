package com.iem.tfm.infrastructure.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iem.tfm.application.port.input.DepartmentGetInputPort;
import com.iem.tfm.domain.model.Department;
import com.iem.tfm.infrastructure.apirest.dto.response.DepartmentResponseDto;
import com.iem.tfm.infrastructure.database.mapper.DepartmentDtoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para exponer endpoints relacionados con departamentos.
 * <p>
 * Actúa como adaptador primario, conectando la capa externa (HTTP) con el caso
 * de uso.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@RestController
@RequestMapping("/department")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class DepartmentController {

	@Autowired
	DepartmentGetInputPort departmentGetInput;

	@Autowired
	DepartmentDtoMapper departmentDtoMapper;

	/**
	 * Endpoint GET para obtener todos los departamentos.
	 * 
	 * @return lista de departamentos como DTOs
	 */
	@GetMapping
	public ResponseEntity<List<DepartmentResponseDto>> getDepartments() {
		log.debug("-> Petición para obtener todos los departamentos recibida <-");

		List<DepartmentResponseDto> responseDtoList = departmentDtoMapper
				.toDtoList(departmentGetInput.getAllDepartment());
		
		if (responseDtoList.isEmpty()) {
			log.warn("-> No se encontraron departamentos <-");
			return ResponseEntity.noContent().build();
		}

		log.debug("-> Departamentos obtenidos exitosamente <-");

		return ResponseEntity.ok(responseDtoList);
	}

	/**
	 * Endpoint GET para obtener un departamento por su ID.
	 * 
	 * @param id identificador del departamento
	 * @return departamento como DTO
	 */
	@GetMapping("/{department-id}")
	public ResponseEntity<DepartmentResponseDto> getDepartment(@PathVariable("department-id") String id) {
		log.debug("-> Petición para obtener un departamento por ID recibida <-");
		
		Department department = departmentGetInput.getDepartment(id);
		
		if (department == null) {
			log.warn("-> Departamento con ID [{}] no encontrado <-", id);
			return ResponseEntity.notFound().build();
		}
		
		DepartmentResponseDto departmentDto = departmentDtoMapper.toDto(department);

		log.debug("-> Departamento obtenido exitosamente <-");

		return ResponseEntity.ok(departmentDto);
	}
}