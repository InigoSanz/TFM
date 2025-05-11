package com.iem.tfm.infrastructure.apirest.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iem.tfm.application.port.input.EmployeeRegisterInputPort;
import com.iem.tfm.infrastructure.apirest.dto.request.EmployeeRequestDto;

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
	
	@PostMapping
	public ResponseEntity<Void> employeeRegister(@RequestBody EmployeeRequestDto employeeDto) {
		log.debug("-> Petición para registrar un empleado recibida <-");
		
		// Aquí habría que mapear el DTO con el command que utiliza el puerto
		
		log.debug("-> Empleado registrado exitosamente <-");
		
		return ResponseEntity.created(crearUri(null)).build(); // Luego cambiamos null por el ID
	}
	
	/**
	 * Método para crear una URI recibiendo un parámetro.
	 * 
	 * @param id
	 * @return uri
	 */
	public static URI crearUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}