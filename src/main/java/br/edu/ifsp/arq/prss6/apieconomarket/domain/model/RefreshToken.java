package br.edu.ifsp.arq.prss6.apieconomarket.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Getter
@Entity
public class RefreshToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	private String userAgent;

	@Column(columnDefinition = "text")
	private String token;
	
	private LocalDateTime dateTime;
	
	public RefreshToken() {
	}
	
	public RefreshToken(User user, String userAgent, String refreshToken, LocalDateTime dateTime) {
		this.user = user;
		this.userAgent = userAgent;
		this.token = refreshToken;
		this.dateTime = dateTime;
	}
}
