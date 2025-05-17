package com.iem.tfm.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.port.input.DepartmentGetInputPort;
import com.iem.tfm.application.port.output.DepartmentRepositoryOutputPort;
import com.iem.tfm.domain.model.Department;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase de Servicio para la obtención de todos los empleados.
 * 
 * Implementa el puerto de entrada {@link DepartmentGetInputPort} y el puerto de salida
 * {@link DepartmentRepositoryOutputPort}.
 * 
 * @author Inigo
 * @version 1.0
 */
@Service
@Slf4j
public class DepartmentGetService implements DepartmentGetInputPort {
	
	@Autowired
	DepartmentRepositoryOutputPort departmentRepositoryOutput;
	
	@Override
	public List<Department> getAllDepartment() {
		log.info("-> Obteniendo todos los departamentos <-");
		
		return departmentRepositoryOutput.findAll();
	}

	@Override
	public Department getDepartment(String id) {
		log.info("-> Obteniendo un departamento <-");
		
		return departmentRepositoryOutput.findDepartmentById(id);
	}

}