package br.edu.ifsp.arq.prss6.apieconomarket.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductListDTO {
	
	private Long id;
	
	private ProductDTO product;
	
	private Integer quantity;
}
