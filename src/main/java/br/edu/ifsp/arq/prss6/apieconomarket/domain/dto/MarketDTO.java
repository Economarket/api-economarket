package br.edu.ifsp.arq.prss6.apieconomarket.domain.dto;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketDTO {

	private String uuid;
	
	private String name;
	
	private String description;
	
	private String logo;
	
	private String locatex;
	
	private String locatey;
	
	private Address address;
}
