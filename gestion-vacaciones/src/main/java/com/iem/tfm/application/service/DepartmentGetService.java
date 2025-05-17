package com.iem.tfm.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.port.input.DepartmentGetInputPort;
import com.iem.tfm.application.port.output.DepartmentRepositoryOutputPort;
import com.iem.tfm.domain.model.Department;

import lombok.extern.slf4j.Slf4j;

/**
 * Servicio de aplicación para consultar departamentos registrados.
 * <p>
 * Implementa el caso de uso definido en {@link DepartmentGetInputPort} y accede
 * a los datos mediante {@link DepartmentRepositoryOutputPort}.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Service
@Slf4j
public class DepartmentGetService implements DepartmentGetInputPort {

	@Autowired
	DepartmentRepositoryOutputPort departmentRepositoryOutput;

	/**
	 * Recupera todos los departamentos existentes.
	 *
	 * @return lista de departamentos
	 */
	@Override
	public List<Department> getAllDepartment() {
		log.info("-> Obteniendo todos los departamentos <-");

		return departmentRepositoryOutput.findAll();
	}

	/**
	 * Recupera un departamento por su ID.
	 *
	 * @param id identificador del departamento
	 * @return departamento correspondiente
	 */
	@Override
	public Department getDepartment(String id) {
		log.info("-> Obteniendo un departamento <-");

		return departmentRepositoryOutput.findDepartmentById(id);
	}
}