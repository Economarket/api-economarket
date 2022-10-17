package br.edu.ifsp.arq.prss6.apieconomarket.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;
	
	private String searchName;
	
	@NotNull
	private Double price;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "brand_id", referencedColumnName = "id")
	private Brand brand;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;
	
	@NotNull
	private String unity;
	
	@NotNull
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "market_with_product",
			joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "market_id")
	)
	private List<Market> markets;
}
