package com.iem.tfm.infrastructure.bootstrap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iem.tfm.infrastructure.database.entity.DepartmentEntity;
import com.iem.tfm.infrastructure.database.repository.DepartmentRepository;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DepartmentLoad {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@PostConstruct
	public void postDepartmentData() {
		List<DepartmentEntity> departmentLoadList = List.of(
				new DepartmentEntity("HR001", "Recursos Humanos"),
				new DepartmentEntity("TS001", "Tiendas"),
				new DepartmentEntity("DR001", "Direccion"),
				new DepartmentEntity("IN001", "IT"),
				new DepartmentEntity("CT001", "Contabilidad")
				);
		
		for (DepartmentEntity department : departmentLoadList) {
			boolean alreadyExists = departmentRepository.existsById(department.getId());
			if(!alreadyExists) {
				departmentRepository.save(department);
			}
		}
		
		log.info("-> Departamentos precargados con exito <-");
	}
}