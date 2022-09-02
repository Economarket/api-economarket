package br.edu.ifsp.arq.prss6.apieconomarket.model.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String uuid;
	
	@NotNull
	private String name;

	@NotNull
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //Propriedade é apenas escrita, não retorna no GET
	private String password;
	
	@NotNull
	@Email
	private String email;
	
	private double experience;
	
	private long permissionId; //Faria mais sentido que o usuário tivesse uma lista de permisões
}
