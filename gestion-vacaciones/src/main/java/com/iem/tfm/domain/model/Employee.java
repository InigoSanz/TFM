package com.iem.tfm.domain.model;

import java.util.Date;
import java.util.List;

import com.iem.tfm.domain.exception.EmployeeDomainException;
import com.iem.tfm.domain.util.DniRules;
import com.iem.tfm.domain.util.EmailRules;

import lombok.Builder;
import lombok.Getter;

/**
 * Modelo de dominio que representa a un empleado.
 * <p>
 * Contiene datos personales como rol, departamentos, fechas, ... Usa el patrón
 * Builder para construir objetos con validaciones de dominio.
 * </p>
 * 
 * @author Inigo
 * @version 1.1
 */
@Getter
@Builder(builderClassName = "Builder")
public class Employee {

	// Atributos inmutables debido al patrón Builder
	private final String id;
	private final String name;
	private final String surname;
	private final String dni;
	private final int age;
	private final String email;
	private final Date startDate;
	private final Date endDate;
	private final List<String> departmentIds;
	private final EmployeeRoleEnum role;

	/**
	 * Constructor privado. Solo se puede crear un empleado mediante Builder.
	 * 
	 * @param builder
	 */
	public Employee(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.surname = builder.surname;
		this.dni = builder.dni;
		this.age = builder.age;
		this.email = builder.email;
		this.startDate = builder.startDate;
		this.endDate = builder.endDate;
		this.departmentIds = builder.departmentIds;
		this.role = builder.role;
	}

	/**
	 * Builder para crear las instancias de Employee. Realiza validaciones antes de
	 * construir el objeto.
	 */
	public static class Builder {
		public Employee build() {

			if (name == null) {
				throw new EmployeeDomainException("El nombre del empleado no puede ser nulo.");
			}

			if (surname == null) {
				throw new EmployeeDomainException("Los apellidos del empleado no pueden ser nulos.");
			}

			if (!DniRules.isValidEmployeeDni(dni)) {
				throw new EmployeeDomainException("El DNI debe tener 8 dígitos y una letra al final.");
			}

			if (age < 16 || age > 65) {
				throw new EmployeeDomainException("Edad inválida para trabajar.");
			}

			if (!EmailRules.isValidEmployeeEmail(email)) {
				throw new EmployeeDomainException("Formato de correo electrónico no válido.");
			}

			if (startDate == null) {
				throw new EmployeeDomainException("La fecha de inicio del empleado no puede ser nula.");
			}

			if (departmentIds == null || departmentIds.isEmpty()) {
				throw new EmployeeDomainException("El empleado debe pertenecer al menos a un departamento.");
			}

			if (role == null) {
				throw new EmployeeDomainException("El empleado debe tener un rol asignado.");
			}

			return new Employee(this);
		}
	}
}