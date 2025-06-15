package com.iem.tfm.application.service;

import java.io.InputStream;
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
	public void registerEmployeesExcel(MultipartFile file) {

		try (InputStream input = file.getInputStream(); Workbook workbook = new XSSFWorkbook(input)) {
			Sheet excelSheet = workbook.getSheetAt(0);

			// Empezamos en la 1 ya que la 0 sería la cabecera de la tabla
			for (int i = 1; i <= excelSheet.getLastRowNum(); i++) {
				Row row = excelSheet.getRow(i);

				if (row == null) {
					continue;
				}

				String name = row.getCell(0).getStringCellValue();
				String surname = row.getCell(1).getStringCellValue();
				String dni = row.getCell(2).getStringCellValue();
				int age = (int) row.getCell(3).getNumericCellValue(); // Casteamos de double a int
				String email = row.getCell(4).getStringCellValue();
				Date startDate = row.getCell(5).getDateCellValue(); // Tendremos que poner el formato correcto de Date
																	// en el Excel
				String departmentList = row.getCell(6).getStringCellValue();
				List<String> departmentIds = Arrays.asList(departmentList.split(","));
				String role = row.getCell(7).getStringCellValue().toUpperCase(); 
				
				// Ahora utilizamos el command de registro para cada iteración
				
			}
		}
	}
}