package com.iem.tfm.infrastructure.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iem.tfm.application.port.input.LoginDoInputPort;
import com.iem.tfm.domain.model.User;
import com.iem.tfm.infrastructure.apirest.dto.request.LoginRequestDto;
import com.iem.tfm.infrastructure.apirest.dto.response.UserResponseDto;
import com.iem.tfm.infrastructure.database.mapper.UserDtoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@RestController
@RequestMapping("/login")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
	@Autowired
	LoginDoInputPort loginDoInputPort;
	
	@Autowired
    UserDtoMapper userDtoMapper;

	@PostMapping
	public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequestDto loginDto) {
		log.debug("-> Petición de login recibida del usuario: {} <-", loginDto.getUsername());
		
		User user = loginDoInputPort.login(loginDto.getUsername(), loginDto.getPassword());
		
		UserResponseDto responseDto = userDtoMapper.toDto(user);
		
		log.debug("-> Login realizado con exito para el usuario: {} <-", responseDto.getUsername());
		
		return ResponseEntity.ok(responseDto);
	}
}