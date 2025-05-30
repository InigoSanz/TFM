package com.iem.tfm.application.command;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationRegisterCommand {
	
	private Date startDate;
	private Date endDate;
	private String employeeId;
}