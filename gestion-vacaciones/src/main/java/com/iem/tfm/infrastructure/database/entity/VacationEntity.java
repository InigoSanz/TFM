package com.iem.tfm.infrastructure.database.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.iem.tfm.domain.util.VacationStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * 
 */
@Document("VACATIONS")
@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class VacationEntity {
	
	@Id
	private String id;
	private Date startDate;
	private Date endDate;
	private String employeeId;
	private VacationStatusEnum status;
}