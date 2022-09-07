package br.edu.ifsp.arq.prss6.apieconomarket.model.dao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "User_tb")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ShoppingList> shoppingLists;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserPermission> userPermissions;
}
