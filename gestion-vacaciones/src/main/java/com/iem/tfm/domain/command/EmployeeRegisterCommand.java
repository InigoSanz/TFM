package com.iem.tfm.domain.command;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase Command de la capa de aplicación que se utiliza para registrar un nuevo
 * {@link com.iem.tfm.domain.model.Employee}.
 * <p>
 * Se utiliza para transportar los datos necesarios desde un adaptador (como un
 * controlador REST) hacia el caso de uso correspondiente en la capa de
 * aplicación.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRegisterCommand {

	private String name;
	private String surname;
	private String dni;
	private int age;
	private String email;
	private Date startDate;
	private List<String> departmentIds;
	private String role;
}