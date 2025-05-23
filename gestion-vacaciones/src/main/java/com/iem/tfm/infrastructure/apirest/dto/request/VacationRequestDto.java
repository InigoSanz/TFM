package com.iem.tfm.infrastructure.apirest.dto.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * 
 */
@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class VacationRequestDto {
	
	private Date startDate;
	private Date endDate;
	private String employeeId;
	private String status;
}