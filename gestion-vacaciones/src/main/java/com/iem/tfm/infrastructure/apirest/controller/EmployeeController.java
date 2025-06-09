package com.iem.tfm.infrastructure.apirest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iem.tfm.application.port.input.EmployeeGetInputPort;
import com.iem.tfm.application.port.input.EmployeeRegisterInputPort;
import com.iem.tfm.domain.command.EmployeeRegisterCommand;
import com.iem.tfm.infrastructure.apirest.dto.request.EmployeeRequestDto;
import com.iem.tfm.infrastructure.apirest.dto.response.EmployeeResponseDto;
import com.iem.tfm.infrastructure.database.mapper.EmployeeDtoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para gestionar los endpoints relacionados con empleados.
 * <p>
 * Expone operaciones para registrar, listar y obtener empleados.
 * Utiliza los puertos de entrada para delegar la lógica a la capa de aplicación.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@RestController
@RequestMapping("/employee")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
	
	@Autowired
	EmployeeRegisterInputPort employeeRegisterInputPort;
	
	@Autowired
	EmployeeGetInputPort employeeGetInputPort;
	
	@Autowired
	EmployeeDtoMapper employeeDtoMapper;
	
	/**
	 * Endpoint para registrar un nuevo empleado.
	 * 
	 * @param employeeDto datos del empleado recibidos por HTTP
	 * @return respuesta con URI del nuevo recurso
	 */
	@PostMapping
	public ResponseEntity<Void> employeeRegister(@RequestBody EmployeeRequestDto employeeDto) {
		log.debug("-> Petición para registrar un empleado recibida <-");
		
		// Mapeamos el DTO a un Command para la capa de aplicación
		EmployeeRegisterCommand registerCommand = employeeDtoMapper.fromDtoToCommand(employeeDto);
		
		// Llamamos al caso de uso y obtenemos el ID generado
		String id = employeeRegisterInputPort.employeeRegister(registerCommand);
		
		log.debug("-> Empleado registrado exitosamente <-");
		
		return ResponseEntity.created(crearUri(id)).build();
	}
	
	/**
	 * Endpoint para obtener todos los empleados registrados.
	 *
	 * @return lista de empleados en formato DTO
	 */
	@GetMapping	
	public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
		log.debug("-> Petición para obtener todos los empleados recibida <-");
		
		List<EmployeeResponseDto> responseDtoList = employeeDtoMapper.fromDomainToDtoList(employeeGetInputPort.getAllEmployees());
		
		log.debug("-> Empleados obtenidos exitosamente <-");
		
		return ResponseEntity.ok(responseDtoList);
	}
	
	/**
	 * Endpoint para obtener un empleado por su ID.
	 *
	 * @param id identificador del empleado
	 * @return empleado en formato DTO
	 */
	@GetMapping("/{employee-id}")
	public ResponseEntity<EmployeeResponseDto> getEmployee(@PathVariable("employee-id") String id) {
		log.debug("-> Petición para obtener un empleado por ID recibida <-");
		
		EmployeeResponseDto employeeDto = employeeDtoMapper.fromDomainToDto(employeeGetInputPort.getEmployee(id));
		
		log.debug("-> Empleado obtenido exitosamente <-");
		
		return ResponseEntity.ok(employeeDto);
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