package com.iem.tfm.application.port.input;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 */
public interface EmployeeBatchRegisterInputPort {
	
	/**
	 * 
	 * @param file
	 */
	public void registerEmployeesExcel(MultipartFile file);
}