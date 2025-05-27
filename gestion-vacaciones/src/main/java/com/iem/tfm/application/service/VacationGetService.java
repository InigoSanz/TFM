package com.iem.tfm.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.tfm.application.port.input.VacationGetInputPort;
import com.iem.tfm.application.port.output.VacationRepositoryOutputPort;
import com.iem.tfm.domain.model.Vacation;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Service
@Slf4j
public class VacationGetService implements VacationGetInputPort {
	
	@Autowired
	VacationRepositoryOutputPort vacationRepositoryOutput;

	@Override
	public List<Vacation> getAllVacation() {
		log.info("-> Obteniendo todas las vacaciones <-");
		
		return vacationRepositoryOutput.findAll();
	}

	@Override
	public Vacation getVacation(String id) {
		log.info("-> Obteniendo unas vacaciones <-");
		
		return vacationRepositoryOutput.findVacationById(id);
	}

	@Override
	public List<Vacation> getEmployeeVacation(String id) {
		log.info("-> Obteniendo las vacaciones de un empleado <-");
		return vacationRepositoryOutput.findVacationByEmployeeId(id);
	}
}