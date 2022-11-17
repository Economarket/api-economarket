package br.edu.ifsp.arq.prss6.apieconomarket.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
	
	private Long id;
	
	private String uuid;

	private String name;
	
	private String searchName;
}
