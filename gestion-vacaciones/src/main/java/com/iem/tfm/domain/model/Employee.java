package com.iem.tfm.domain.model;

import java.util.Date;
import java.util.List;

import com.iem.tfm.domain.util.EmployeeRoleEnum;

/**
 * Modelo de dominio para los empleados.
 * 
 * Encapsula los atributos personales, departamentos y roles de los empleados.
 * 
 * Utilizamos el patrón Builder para evitar constructores extensos, mejorar
 * el diseño y mantener el dominio libre de dependencias.
 * 
 * @author Inigo
 * @version 1.0
 */
public class Employee {
	
	private final Long id;
	private final String name;
	private final String surname;
	private final String dni;
	private final int age;
	private final String email;
	private final Date startDate;
	private final Date endDate;
	private final List<Department> departments;
	private final EmployeeRoleEnum role;
	
	// Sonar se queja por que tiene más de 7 parámetros, utilizaremos el patrón Builder
	public Employee(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.surname = builder.surname;
		this.dni = builder.dni;
		this.age = builder.age;
		this.email = builder.email;
		this.startDate = builder.startDate;
		this.endDate = builder.endDate;
		this.departments = builder.departments;
		this.role = builder.role;
	}
	
	public static class Builder {
		private Long id;
        private String name;
        private String surname;
        private String dni;
        private int age;
        private String email;
        private Date startDate;
        private Date endDate;
        private List<Department> departments;
        private EmployeeRoleEnum role;
        
        public Builder id(Long id) {
        	this.id = id;
        	return this;
        }
        
        public Builder name(String name) {
        	this.name = name;
        	return this;
        }
        
        public Builder surname(String surname) {
        	this.surname = surname;
        	return this;
        }
        
        public Builder dni(String dni) {
        	this.dni = dni;
        	return this;
        }
        
        public Builder age(int age) {
        	this.age = age;
        	return this;
        }
        
        public Builder email(String email) {
        	this.email = email;
        	return this;
        }
        
        public Builder startDate(Date startDate) {
        	this.startDate = startDate;
        	return this;
        }
        
        public Builder endDate(Date endDate) {
        	this.endDate = endDate;
        	return this;
        }
        
        public Builder departments(List<Department> departments) {
        	this.departments = departments;
        	return this;
        }
        
        public Builder role(EmployeeRoleEnum role) {
        	this.role = role;
        	return this;
        }
        
        public Employee build() {
        	return new Employee(this);
        }
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getDni() {
		return dni;
	}

	public int getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public EmployeeRoleEnum getRole() {
		return role;
	}
}