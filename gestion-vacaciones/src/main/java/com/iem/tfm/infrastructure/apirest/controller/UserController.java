package com.iem.tfm.infrastructure.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iem.tfm.application.port.input.UserGetInputPort;
import com.iem.tfm.infrastructure.apirest.dto.response.UserResponseDto;
import com.iem.tfm.infrastructure.database.mapper.UserDtoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para la gestión de usuarios.
 * 
 * Este controlador expone los endpoints necesarios para recuperar información
 * de usuarios desde el sistema. Se comunica con la capa de aplicación a través
 * del puerto de entrada {@link UserGetInputPort}.
 * 
 * Los datos se devuelven en formato {@link UserResponseDto}.
 * 
 * @author Inigo
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	UserGetInputPort userGetInputPort;
	
	@Autowired
	UserDtoMapper userDtoMapper;
	
	/**
	 * Obtiene la lista completa de usuarios registrados en el sistema.
	 * 
	 * @return una respuesta HTTP con una lista de {@link UserResponseDto} representando todos los usuarios.
	 */
	@GetMapping
	public ResponseEntity<List<UserResponseDto>> getAllUsers() {
		log.debug("-> Petición para obtener todos los usuarios recibida <-");
	
		List<UserResponseDto> responseDtoList = userDtoMapper.toDtoList(userGetInputPort.getAllusers());
		
		log.debug("-> Usuarios obtenidos exitosamente <-");
	
		return ResponseEntity.ok(responseDtoList);
	}
	
	/**
	 * Obtiene los detalles de un usuario específico por su identificador único.
	 * 
	 * @param id identificador del usuario que se desea consultar.
	 * @return una respuesta HTTP con los datos del usuario como {@link UserResponseDto}.
	 */
	@GetMapping("/{user-id}")
	public ResponseEntity<UserResponseDto> getUser(@PathVariable("user-id") String id) {
		log.debug("-> Petición para obtener un usuario por ID recibida <-");
		
		UserResponseDto userDto = userDtoMapper.toDto(userGetInputPort.getUserById(id));
		
		log.debug("-> Usuario obtenido exitosamente <-");
		
		return ResponseEntity.ok(userDto);
	}
}