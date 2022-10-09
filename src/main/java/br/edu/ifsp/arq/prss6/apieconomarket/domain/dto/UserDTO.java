package br.edu.ifsp.arq.prss6.apieconomarket.domain.dto;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	
	private Long id;

	private String uuid;
	
	private String name;
	
	private String email;
	
	private Double experience;
	
	private Address address;
}
