package com.iem.tfm.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VacationStatusChangeCommand {
	
	private String vacationId;
	private String employeeId;
	private String role;
	private boolean approve;
}