package com.iem.tfm.application.command;

import java.util.Date;
import java.util.List;

/**
 * Clase command que se utiliza para registrar un nuevo empleado.
 * 
 * Transporta los datos necesarios para ejecutar el caso de uso del alta
 * de un empleado desde un adaptador externo.
 * 
 * @author Inigo
 * @version 1.0
 */
public class EmployeeRegisterCommand {
	
	private String name;
	private String surname;
	private String dni;
	private int age;
	private String email;
	private Date startDate;
	private List<Long> departmentIds;
	private String role;
	
	public EmployeeRegisterCommand() {
		// Constructor por defecto
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<Long> getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(List<Long> departmentIds) {
		this.departmentIds = departmentIds;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}