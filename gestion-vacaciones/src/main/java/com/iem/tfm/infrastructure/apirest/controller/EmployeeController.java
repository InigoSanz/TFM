package com.iem.tfm.infrastructure.apirest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iem.tfm.application.command.EmployeeRegisterCommand;
import com.iem.tfm.application.port.input.EmployeeGetInputPort;
import com.iem.tfm.application.port.input.EmployeeRegisterInputPort;
import com.iem.tfm.infrastructure.apirest.dto.request.EmployeeRequestDto;
import com.iem.tfm.infrastructure.apirest.dto.response.EmployeeResponseDto;
import com.iem.tfm.infrastructure.database.mapper.EmployeeDtoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para los endpoints de los empleados.
 * 
 * @author Inigo
 * @version 1.0
 */
@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
	
	@Autowired
	EmployeeRegisterInputPort employeeRegisterInputPort;
	
	@Autowired
	EmployeeGetInputPort employeeGetInputPort;
	
	@Autowired
	EmployeeDtoMapper employeeDtoMapper;
	
	@PostMapping
	public ResponseEntity<Void> employeeRegister(@RequestBody EmployeeRequestDto employeeDto) {
		log.debug("-> Petición para registrar un empleado recibida <-");
		
		// Mapeamos el DTO a Command para que lo utilice la capa de aplicación
		EmployeeRegisterCommand registerCommand = employeeDtoMapper.fromDtoToCommand(employeeDto);
		// Lo persistimos
		String id = employeeRegisterInputPort.employeeRegister(registerCommand);
		
		log.debug("-> Empleado registrado exitosamente <-");
		
		return ResponseEntity.created(crearUri(id)).build();
	}
	
	@GetMapping	
	public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
		log.debug("-> Petición para obtener todos los empleados recibida <-");
		
		List<EmployeeResponseDto> responseDtoList = employeeDtoMapper.fromDomainToDtoList(employeeGetInputPort.getAllEmployees());
		
		log.debug("-> Empleados obtenidos exitosamente <-");
		
		return ResponseEntity.ok(responseDtoList);
	}
	
	@GetMapping("/{employee-id}")
	public ResponseEntity<EmployeeResponseDto> getEmployee(@PathVariable("employee-id") String id) {
		log.debug("-> Petición para obtener un empleado por ID recibida <-");
		
		EmployeeResponseDto employeeDto = employeeDtoMapper.fromDomainToDto(employeeGetInputPort.getEmployee(id));
		
		log.debug("-> Empleado obtenido exitosamente <-");
		
		return ResponseEntity.ok(employeeDto);
	}
	
	/**
	 * Método para crear una URI recibiendo un parámetro.
	 * 
	 * @param id
	 * @return uri
	 */
	public static URI crearUri(String id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}