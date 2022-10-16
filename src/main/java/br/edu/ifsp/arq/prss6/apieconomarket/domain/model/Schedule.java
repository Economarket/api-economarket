package br.edu.ifsp.arq.prss6.apieconomarket.domain.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedule")
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime oppeningHour;
	
	@NotNull
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime closingHour;
	
	@NotNull
	private Integer dayOfWeek;
	
	@NotNull
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "market_id")
	private Market market;
}
