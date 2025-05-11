package com.iem.tfm.application.port.output;

import com.iem.tfm.domain.model.Employee;

/**
 * Puerto de salida, repositorio para guardar las operaciones con los empleados.
 * 
 * Define las acciones que los casos de uso requieren en la capa de aplicación.
 * 
 * Se implementa más adelante por un adaptador en la capa de infraestructura.
 * 
 * @author Inigo
 * @version 1.0
 */
public interface EmployeeRepositoryOutputPort {
	
	public void save(Employee employee);
}
