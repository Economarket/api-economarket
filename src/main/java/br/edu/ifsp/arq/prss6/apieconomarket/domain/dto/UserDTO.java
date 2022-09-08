package br.edu.ifsp.arq.prss6.apieconomarket.domain.dto;

import lombok.Data;

@Data
public class UserDTO {

	private String uuid;
	
	private String name;
	
	private String email;
	
	private Double experience;
}
