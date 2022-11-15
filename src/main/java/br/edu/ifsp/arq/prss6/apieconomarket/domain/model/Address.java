package br.edu.ifsp.arq.prss6.apieconomarket.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.edu.ifsp.arq.prss6.apieconomarket.utils.UtilsCons;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address", uniqueConstraints = { @UniqueConstraint(columnNames = { "cep" }) })
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Pattern(regexp = UtilsCons.CEP_REGEX)
	private String cep;
	
	@NotNull
	private String street;
	
	@NotNull
	private String number;
	
	private String complement;
	
	@NotNull
	private String district;
	
	@NotNull
	private String city;
	
	@NotNull
	private String state;
	
	@NotNull
	private String locateX;
	
	@NotNull
	private String locateY;
}
