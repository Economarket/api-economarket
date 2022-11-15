package br.edu.ifsp.arq.prss6.apieconomarket.domain.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingListDTO {

	private Long id;
	
	private String name;
	
	private List<ProductListDTO> productList;
}
