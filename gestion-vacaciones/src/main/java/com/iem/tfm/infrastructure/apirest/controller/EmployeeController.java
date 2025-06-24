package com.iem.tfm.infrastructure.apirest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iem.tfm.application.port.input.EmployeeBatchRegisterInputPort;
import com.iem.tfm.application.port.input.EmployeeDeactivateInputPort;
import com.iem.tfm.application.port.input.EmployeeGetInputPort;
import com.iem.tfm.application.port.input.EmployeeRegisterInputPort;
import com.iem.tfm.application.port.input.EmployeeUpdateInputPort;
import com.iem.tfm.domain.command.EmployeeRegisterCommand;
import com.iem.tfm.domain.command.EmployeeUpdateCommand;
import com.iem.tfm.domain.exception.EmployeeDomainException;
import com.iem.tfm.domain.model.Employee;
import com.iem.tfm.infrastructure.apirest.dto.request.EmployeeRequestDto;
import com.iem.tfm.infrastructure.apirest.dto.request.EmployeeUpdateRequestDto;
import com.iem.tfm.infrastructure.apirest.dto.response.EmployeeBatchRegisterResponseDto;
import com.iem.tfm.infrastructure.apirest.dto.response.EmployeeResponseDto;
import com.iem.tfm.infrastructure.database.mapper.EmployeeDtoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para gestionar los endpoints relacionados con empleados.
 * <p>
 * Expone operaciones para registrar, listar y obtener empleados. Utiliza los
 * puertos de entrada para delegar la lógica a la capa de aplicación.
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
	EmployeeUpdateInputPort employeeUpdateInputPort;

	@Autowired
	EmployeeDtoMapper employeeDtoMapper;
	
	@Autowired
	EmployeeDeactivateInputPort employeeDeactivateInputPort;
	
	@Autowired
	EmployeeBatchRegisterInputPort employeeBatchRegisterInputPort;

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
	 * 
	 * @param file
	 * @return
	 */
	@PostMapping("/batch-upload")
	public ResponseEntity<EmployeeBatchRegisterResponseDto> employeeRegisterExcel(@RequestParam MultipartFile file) {
		log.debug("-> Petición para carga masiva de empleados mediante Excel recibida <-");

	    try {
	        EmployeeBatchRegisterResponseDto resumen = employeeBatchRegisterInputPort.registerEmployeesExcel(file);

	        int total = resumen.getTotal();
	        int fallos = resumen.getErrors().size();

	        if (fallos == 0) {
	            log.debug("-> Todos los empleados se registraron exitosamente <-");
	            return ResponseEntity.ok(resumen);
	        } else if (fallos < total) {
	            log.warn("-> Algunos empleados no se pudieron registrar <-");
	            return ResponseEntity.status(207).body(resumen); // 207 Multi-Status
	        } else {
	            log.error("-> Fallo total: ningún empleado fue registrado <-");
	            return ResponseEntity.badRequest().body(resumen); // 400 Bad Request
	        }

	    } catch (EmployeeDomainException ex) {
	        log.error("-> Error al procesar el archivo Excel <-");
	        return ResponseEntity.status(500).build(); // Internal Server Error
	    }
	}

	/**
	 * Endpoint para obtener todos los empleados registrados.
	 *
	 * @return lista de empleados en formato DTO
	 */
	@GetMapping
	public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
		log.debug("-> Petición para obtener todos los empleados recibida <-");

		List<EmployeeResponseDto> responseDtoList = employeeDtoMapper
				.fromDomainToDtoList(employeeGetInputPort.getAllEmployees());

		if (responseDtoList.isEmpty()) {
			log.warn("-> No se encontraron empleados registrados <-");
			return ResponseEntity.noContent().build();
		}

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

		Employee employee = employeeGetInputPort.getEmployee(id);

		if (employee == null) {
			log.warn("-> Empleado con ID [{}] no encontrado <-", id);
			return ResponseEntity.notFound().build();
		}

		EmployeeResponseDto employeeDto = employeeDtoMapper.fromDomainToDto(employee);

		log.debug("-> Empleado obtenido exitosamente <-");

		return ResponseEntity.ok(employeeDto);
	}

	/**
	 * 
	 * @param departmentIds
	 * @return
	 */
	@GetMapping("/department/{department-id}")
	public ResponseEntity<List<EmployeeResponseDto>> getEmployeesByDepartment(
			@PathVariable("department-id") List<String> departmentIds) {
		log.debug("-> Petición para obtener empleados del departamento con ID: [{}] <-", departmentIds);

		List<Employee> employees = employeeGetInputPort.getEmployeesByDepartment(departmentIds);

		if (employees.isEmpty()) {
			log.warn("-> No hay empleados registrados en este departamento <-");
			return ResponseEntity.noContent().build();
		}

		List<EmployeeResponseDto> responseDtoList = employeeDtoMapper.fromDomainToDtoList(employees);

		log.debug("-> Empleados del departamento obtenidos exitosamente <-");

		return ResponseEntity.ok(responseDtoList);
	}
	
	/**
	 * Actualiza los datos modificables de un empleado existente.
	 *
	 * @param id  identificador del empleado
	 * @param dto datos nuevos del empleado
	 * @return respuesta con estado 200 OK si la operación fue exitosa
	 */
	@PutMapping("/{employee-id}")
	public ResponseEntity<Void> updateEmployee(@PathVariable("employee-id") String id,
			@RequestBody EmployeeUpdateRequestDto dto) {
		log.debug("-> Petición para actualizar empleado con id: {} recibida <-", id);

		EmployeeUpdateCommand commandUpdate = employeeDtoMapper.fromDtoToUpdateCommand(id, dto);
		
		employeeUpdateInputPort.updateEmployee(commandUpdate);
		
		log.debug("-> Empleado con id: {} actualizado exitosamente <-", id);
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@PatchMapping("/{employee-id}/deactivate")
	public ResponseEntity<Void> deactivateEmployee(@PathVariable("employee-id") String id) {
		log.debug("-> Petición para dar de baja empleado con id: {} recibida <-", id);
		
		employeeDeactivateInputPort.deactivateEmployee(id);
		
		log.debug("-> Empleado con id: {} dado de baja exitosamente <-", id);
		
		return ResponseEntity.ok().build();
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