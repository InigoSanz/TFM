package com.iem.tfm.domain.command;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * No lleva NoArgsConstructor por los parametros final
 */
@Data
@AllArgsConstructor
public class EmployeeUpdateCommand {
	
	private final String id;
	private final int age;
	private final String email;
	private final Date startDate;
	private final Date endDate;
	private final List<String> departmentIds;
	private final String role;
}