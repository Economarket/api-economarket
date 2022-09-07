package br.edu.ifsp.arq.prss6.apieconomarket.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String searchName;
	
	@NotNull
	private String brandName;
	
	@OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<Product> product = new ArrayList<>();
}
