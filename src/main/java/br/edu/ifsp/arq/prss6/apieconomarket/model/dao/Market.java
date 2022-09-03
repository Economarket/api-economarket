package br.edu.ifsp.arq.prss6.apieconomarket.model.dao;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Market {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String uuid;
	
	@NotNull
	private String locateX;
	
	@NotNull
	private String locateY;
	
	@NotNull
	private String name;
	
	private String description;
	
	private String logo;
	
	@NotNull
	private List<Address> address;
	
	private List<Schedule> schedules;
}
