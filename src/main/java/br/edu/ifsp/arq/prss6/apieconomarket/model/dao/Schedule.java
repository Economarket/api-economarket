package br.edu.ifsp.arq.prss6.apieconomarket.model.dao;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private LocalTime oppeningHour;
	
	@NotNull
	private LocalTime closingHour;
	
	@NotNull
	private Integer dayOfWeek;
}
