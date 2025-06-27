package com.iem.tfm.application.port.input;

import org.springframework.web.multipart.MultipartFile;

import com.iem.tfm.infrastructure.apirest.dto.response.EmployeeBatchRegisterResponseDto;

/**
 * Puerto de entrada para el registro masivo de empleados a través de un archivo
 * Excel.
 * <p>
 * Define la operación para procesar un archivo Excel y registrar empleados en
 * el sistema desde adaptadores externos.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public interface EmployeeBatchRegisterInputPort {

	/**
	 * Registra múltiples empleados a partir de un archivo Excel proporcionado.
	 *
	 * @param file archivo Excel que contiene la información de los empleados
	 */
	public EmployeeBatchRegisterResponseDto registerEmployeesExcel(MultipartFile file);
}