package com.iem.tfm.application.command;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@NoArgsConstructor
public class VacationRegisterCommand {
	
	private Date startDate;
	private Date endDate;
	private String employeeId;
	private String status;
}