package br.edu.ifsp.arq.prss6.apieconomarket.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor	
@Entity
@Table(name = "User_tb", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String uuid = UUID.randomUUID().toString();
	
	@NotNull
	private String name;

	@NotNull
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //Propriedade é apenas escrita, não retorna no GET
	@Size(min = 8)
	private String password;
	
	@NotNull
	@Email
	private String email;
	
	@JsonProperty(access = Access.READ_ONLY)
	private Double experience = 0.0;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<ShoppingList> shoppingLists;
	
	@JsonProperty(access = Access.READ_ONLY)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "user_permission",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "permission_id")
	)
	private List<Permission> permissions = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<RefreshToken> tokens;
}
