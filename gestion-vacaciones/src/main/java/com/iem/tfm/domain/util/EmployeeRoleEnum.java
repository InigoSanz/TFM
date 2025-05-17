package com.iem.tfm.domain.util;

/**
 * Enum que representa los roles posibles de un {@link com.iem.tfm.domain.model.Employee} dentro de la empresa.
 * <p>
 * Los roles definen el nivel de responsabilidad y acceso del empleado:
 * </p>
 * <ul>
 *   <li><strong>NORMAL</strong>: Empleado estándar sin permisos especiales.</li>
 *   <li><strong>ENCARGADO</strong>: Responsable de equipo o departamento.</li>
 *   <li><strong>RRHH</strong>: Personal del área de Recursos Humanos.</li>
 * </ul>
 * 
 * @author Iñigo
 * @version 1.0
 */
public enum EmployeeRoleEnum {
	NORMAL, ENCARGADO, RRHH
}