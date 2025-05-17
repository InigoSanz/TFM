package com.iem.tfm.application.port.input;

import java.util.List;

import com.iem.tfm.domain.model.Department;

/**
 * Puerto de entrada para la obtención de departamentos.
 * 
 * @author Inigo
 * @version 1.0
 */
public interface DepartmentGetInputPort {

	public List<Department> getAllDepartment();

	public Department getDepartment(String id);
}