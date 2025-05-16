package com.iem.tfm.application.port.output;

import java.util.List;

import com.iem.tfm.domain.model.Department;

/**
* Puerto de salida, repositorio para guardar las operaciones con los departamentos.
* 
* Define las acciones que los casos de uso requieren en la capa de aplicación.
* 
* Se implementa más adelante por un adaptador en la capa de infraestructura.
* 
* @author Inigo
* @version 1.0
*/
public interface DepartmentRepositoryOutputPort {
	
	public List<Department> findAllById(List<String> ids);

	public List<Department> findAll();
}