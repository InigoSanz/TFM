package com.iem.tfm.application.port.input;

import org.springframework.web.multipart.MultipartFile;

import com.iem.tfm.infrastructure.apirest.dto.response.EmployeeBatchRegisterResponseDto;

/**
 * 
 */
public interface EmployeeBatchRegisterInputPort {
	
	/**
	 * 
	 * @param file
	 */
	public EmployeeBatchRegisterResponseDto registerEmployeesExcel(MultipartFile file);
}