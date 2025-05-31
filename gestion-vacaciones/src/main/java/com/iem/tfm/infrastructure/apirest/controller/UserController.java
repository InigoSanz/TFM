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
 * 
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
	
	@GetMapping
	public ResponseEntity<List<UserResponseDto>> getAllUsers() {
		log.debug("-> Petición para obtener todos los usuarios recibida <-");
	
		List<UserResponseDto> responseDtoList = userDtoMapper.toDtoList(userGetInputPort.getAllusers());
		
		log.debug("-> Usuarios obtenidos exitosamente <-");
	
		return ResponseEntity.ok(responseDtoList);
	}
	
	@GetMapping("/{user-id}")
	public ResponseEntity<UserResponseDto> getUser(@PathVariable("user-id") String id) {
		log.debug("-> Petición para obtener un usuario por ID recibida <-");
		
		UserResponseDto userDto = userDtoMapper.toDto(userGetInputPort.getUserById(id));
		
		log.debug("-> Usuario obtenido exitosamente <-");
		
		return ResponseEntity.ok(userDto);
	}
}