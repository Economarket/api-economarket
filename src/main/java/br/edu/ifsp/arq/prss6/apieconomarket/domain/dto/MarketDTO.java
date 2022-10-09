package br.edu.ifsp.arq.prss6.apieconomarket.domain.dto;

import java.util.List;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Address;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Schedule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketDTO {
	
	private Long id;

	private String uuid;
	
	private String name;
	
	private String description;
	
	private String logo;
	
	private String locatex;
	
	private String locatey;
	
	private Address address;
	
	private List<Schedule> schedules;
}
