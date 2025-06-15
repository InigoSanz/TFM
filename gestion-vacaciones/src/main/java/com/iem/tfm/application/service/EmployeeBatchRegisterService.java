package com.iem.tfm.application.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iem.tfm.application.port.input.EmployeeBatchRegisterInputPort;
import com.iem.tfm.application.port.input.EmployeeRegisterInputPort;
import com.iem.tfm.domain.command.EmployeeRegisterCommand;
import com.iem.tfm.domain.exception.EmployeeDomainException;
import com.iem.tfm.infrastructure.apirest.dto.response.EmployeeBatchRegisterResponseDto;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Service
@Slf4j
public class EmployeeBatchRegisterService implements EmployeeBatchRegisterInputPort {

	@Autowired
	EmployeeRegisterInputPort employeeRegisterInputPort;

	@Override
	public EmployeeBatchRegisterResponseDto registerEmployeesExcel(MultipartFile file) {
		int total = 0;
		List<String> registerEmployees = new ArrayList<>();
		List<String> errors = new ArrayList<>();

		try (InputStream input = file.getInputStream(); Workbook workbook = new XSSFWorkbook(input)) {
			Sheet excelSheet = workbook.getSheetAt(0);

			// Empezamos en la 1 ya que la 0 sería la cabecera de la tabla
			for (int i = 1; i <= excelSheet.getLastRowNum(); i++) {
				Row row = excelSheet.getRow(i);

				if (row == null) {
					continue;
				}

				total++;

				try {
					String name = row.getCell(0).getStringCellValue();
					String surname = row.getCell(1).getStringCellValue();
					String dni = row.getCell(2).getStringCellValue();
					int age = (int) row.getCell(3).getNumericCellValue(); // Casteamos de double a int
					String email = row.getCell(4).getStringCellValue();
					Date startDate = row.getCell(5).getDateCellValue(); // Tendremos que poner el formato correcto de
																		// Date
																		// en el Excel
					String departmentList = row.getCell(6).getStringCellValue();
					List<String> departmentIds = Arrays.asList(departmentList.split(","));
					String role = row.getCell(7).getStringCellValue().toUpperCase();

					// Ahora utilizamos el command de registro para cada iteración
					EmployeeRegisterCommand registerCommand = new EmployeeRegisterCommand(name, surname, dni, age,
							email, startDate, departmentIds, role);

					log.debug("Dando de alta: {} {}", name, surname);
					employeeRegisterInputPort.employeeRegister(registerCommand);

					registerEmployees.add(name + " " + surname + " (" + email + ")"); 
				} catch (Exception rowError) {
					errors.add(null);
				}
			}
		} catch (Exception ex) {
			log.error("Error al procesar el Excel de empleados", ex);
			throw new EmployeeDomainException("No se ha podido procesar el Excel: ", ex.getMessage()); // Creamos nueva
																										// excepción
		}

		return new EmployeeBatchRegisterResponseDto(total, registerEmployees, errors);
	}
}