package br.edu.ifsp.arq.prss6.apieconomarket.domain.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
	
	private Long id;

	private String name;
	
	private Double price;
	
	private CategoryDTO category;
	
	private BrandDTO brand;
	
	private List<MarketDTO> markets;
	
	private String unity;
	
	private Boolean greaterThanLastPrice;
}
