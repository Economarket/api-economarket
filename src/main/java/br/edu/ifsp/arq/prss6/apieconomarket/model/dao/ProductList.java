package br.edu.ifsp.arq.prss6.apieconomarket.model.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
@Entity
public class ProductList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String uuid;
	
	@ManyToOne
	@JoinColumn(name = "shopping_list_id")
	private ShoppingList shoppingList;
	
	@OneToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
	
	@NotNull
	@Positive
	private Integer quantity;
}
