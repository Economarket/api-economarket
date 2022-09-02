package br.edu.ifsp.arq.prss6.apieconomarket.model.dao;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private BigDecimal price;
	
	@NotNull
	private long brandId;
	
	@NotNull
	private long categoryId;
	
	@NotNull
	private Integer unity;
}
