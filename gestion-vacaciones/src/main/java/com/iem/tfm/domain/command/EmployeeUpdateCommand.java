package com.iem.tfm.domain.command;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Comando del dominio que representa una solicitud para actualizar los datos de
 * un empleado.
 * <p>
 * Este objeto se utiliza para transferir información desde la capa de
 * aplicación hacia el dominio cuando se desea modificar un empleado existente.
 * Los campos pueden venir con valores {@code null} si no se desea modificar esa
 * propiedad concreta.
 * </p>
 * 
 * <p>
 * <b>Nota:</b> No lleva {@code @NoArgsConstructor} debido a que los campos son
 * {@code final}.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class EmployeeUpdateCommand {

	private final String id;
	private final int age;
	private final String email;
	private final Date startDate;
	private final Date endDate;
	private final List<String> departmentIds;
	private final String role;
}