package com.iem.tfm.application.command;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase command que se utiliza para registrar un nuevo empleado.
 * 
 * Transporta los datos necesarios para ejecutar el caso de uso del alta
 * de un empleado desde un adaptador externo.
 * 
 * @author Inigo
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class EmployeeRegisterCommand {
	
	private String name;
	private String surname;
	private String dni;
	private int age;
	private String email;
	private Date startDate;
	private List<Long> departmentIds;
	private String role;
}