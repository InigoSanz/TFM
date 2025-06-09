package com.iem.tfm.domain.model;


/**
 * Enum que representa los roles posibles de los usuarios en la aplicación.
 * <p>
 * Define el nivel de permisos dentro del sistema:
 * </p>
 * <ul>
 *   <li><strong>USER</strong>: Usuario estándar con acceso limitado.</li>
 *   <li><strong>ADMIN</strong>: Usuario administrador con acceso completo.</li>
 * </ul>
 * 
 * @author Inigo
 * @version 1.0
 */
public enum UserRoleEnum {
	USER, ADMIN
}