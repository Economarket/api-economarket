package br.edu.ifsp.arq.prss6.apieconomarket.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTValidateFilter extends BasicAuthenticationFilter {
	
	public static final String HEADER_ATTRIBUTE = "Authorization";
	
	public static final String BEARER_ATTRIBUTE_PREFIX = "Bearer ";

	public JWTValidateFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String attribute = request.getHeader(HEADER_ATTRIBUTE);
		
		if(attribute == null || !attribute.startsWith(BEARER_ATTRIBUTE_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		String token = attribute.replace(BEARER_ATTRIBUTE_PREFIX, "");
		
		UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
		
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
		//TODO: Alterar token para arquivo de configuração
		DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(JWTAuthFilter.TOKEN_SECRET))
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
		
		//TODO: Configurar permissões do usuário
		return new UsernamePasswordAuthenticationToken(user, null, authorities);
	}
}
