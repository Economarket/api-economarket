package br.edu.ifsp.arq.prss6.apieconomarket.model.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String cep;
	
	@NotNull
	private String street;
	
	@NotNull
	private String number;
	
	private String complement;
	
	@NotNull
	private String district;
	
	@NotNull
	private String city;
	
	@NotNull
	private String state;
}
