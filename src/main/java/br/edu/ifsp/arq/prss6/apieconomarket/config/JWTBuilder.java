package br.edu.ifsp.arq.prss6.apieconomarket.config;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.edu.ifsp.arq.prss6.apieconomarket.utils.UtilsCons;

public class JWTBuilder {

	public static String createAccessToken(String subject, List<String> userRoles, 
			TokenTypeEnum tokenType) {
		return JWT.create()
				.withSubject(subject)
				.withExpiresAt(
						new Date(System.currentTimeMillis() +
								JWTParametersConfig.getExpirationTime(tokenType)))
				.withClaim(UtilsCons.ROLE_CLAIM_NAME, userRoles)
				.sign(getAlgorithm());
	}
	
	public static Algorithm getAlgorithm() {
		return Algorithm.HMAC512(JWTParametersConfig.TOKEN_SECRET);
	}
	
	public static DecodedJWT getDecodedJWT(String token) {
		return JWT.require(getAlgorithm())
				.build()
				.verify(token);
	}
	
	public static UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
		DecodedJWT decodedJWT = JWT.require(getAlgorithm())
				.build()
				.verify(token);
		
		String user = decodedJWT.getSubject();
		
		Collection<? extends GrantedAuthority> authorities;
		
		authorities = decodedJWT.getClaim("roles").asList(String.class)
			.stream()
			.map(r -> new SimpleGrantedAuthority(r))
			.collect(Collectors.toList());
		
		if(user == null) {
			return null;
		}
		
		return new UsernamePasswordAuthenticationToken(user, null, authorities);
	}
}
