package br.edu.ifsp.arq.prss6.apieconomarket.model.dao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Market {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	@OneToOne
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
	@OneToMany(mappedBy = "market", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Schedule> schedules;
	
	@OneToMany(mappedBy = "market", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<MarketWithProduct> marketWithProduct;
}
