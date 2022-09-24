package br.edu.ifsp.arq.prss6.apieconomarket.domain.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

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
public class Market {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String uuid = UUID.randomUUID().toString();
	
	@NotNull
	private String locateX;
	
	@NotNull
	private String locateY;
	
	@NotNull
	private String name;
	
	private String description;
	
	private String logo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
	@OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
	private List<Schedule> schedules;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "markets", cascade = CascadeType.ALL)
	private List<Product> products;
}
