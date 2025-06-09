package com.iem.tfm.infrastructure.apirest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iem.tfm.application.port.input.DepartmentGetInputPort;
import com.iem.tfm.application.port.input.EmployeeGetInputPort;
import com.iem.tfm.application.port.input.LoginDoInputPort;
import com.iem.tfm.domain.model.Employee;
import com.iem.tfm.domain.model.EmployeeRoleEnum;
import com.iem.tfm.domain.model.User;
import com.iem.tfm.infrastructure.apirest.dto.request.LoginRequestDto;
import com.iem.tfm.infrastructure.apirest.dto.response.LoginResponseDto;
import com.iem.tfm.infrastructure.database.mapper.UserDtoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST que gestiona las peticiones de login de usuarios.
 * <p>
 * Este controlador recibe las credenciales desde el frontend y delega la
 * autenticación en la capa de aplicación a través del puerto
 * {@link LoginDoInputPort}. Si las credenciales son válidas, devuelve los datos
 * del usuario autenticado.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
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

	@Autowired
	EmployeeGetInputPort employeeGetInputPort;

	@Autowired
	DepartmentGetInputPort departmentGetInputPort;

	/**
	 * Realiza la autenticación del usuario con las credenciales proporcionadas.
	 * 
	 * @param loginDto objeto que contiene el nombre de usuario y la contraseña
	 * @return respuesta con el DTO del usuario o error si las credenciales no son
	 *         válidas
	 */
	@PostMapping
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginDto) {
		log.debug("-> Petición de login recibida del usuario: {} <-", loginDto.getUsername());

		User user = loginDoInputPort.login(loginDto.getUsername(), loginDto.getPassword());
		
		if (user == null) {
			log.warn("-> Usuario o contraseña incorrectos (login falso) para: {} <-", loginDto.getUsername());
			return ResponseEntity.badRequest().build();
		}
		
		EmployeeRoleEnum employeeRole = null;
		List<String> departmentIds = new ArrayList<>();
		List<String> departmentNames = new ArrayList<>();

		if (user.getEmployeeId() != null) {
			Employee employee = employeeGetInputPort.getEmployee(user.getEmployeeId());
			employeeRole = employee.getRole();

			departmentIds = employee.getDepartmentIds();

			if (departmentIds != null && !departmentIds.isEmpty()) {
				departmentNames = departmentIds.stream().map(id -> departmentGetInputPort.getDepartment(id).getName())
						.toList();
			}
		}

		LoginResponseDto responseDto = userDtoMapper.toLoginDtoLogin(user, employeeRole, departmentIds,
				departmentNames);

		log.debug("-> Login realizado con éxito para el usuario: {} <-", responseDto.getUsername());

		return ResponseEntity.ok(responseDto);
	}
}