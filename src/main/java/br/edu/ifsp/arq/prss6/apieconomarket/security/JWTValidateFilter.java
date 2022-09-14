package br.edu.ifsp.arq.prss6.apieconomarket.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifsp.arq.prss6.apieconomarket.config.JWTBuilder;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.UtilsCons;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTValidateFilter extends BasicAuthenticationFilter {

	public JWTValidateFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			String attribute = request.getHeader(UtilsCons.HEADER_ATTRIBUTE);
			
			if(attribute == null || !attribute.startsWith(UtilsCons.BEARER_ATTRIBUTE_PREFIX)) {
				chain.doFilter(request, response);
				return;
			}
			
			String token = attribute.replace(UtilsCons.BEARER_ATTRIBUTE_PREFIX, "");
			
			UsernamePasswordAuthenticationToken authenticationToken = JWTBuilder.getAuthenticationToken(token);
			
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			
			chain.doFilter(request, response);
		}
		catch(Exception e) {
			log.error("Erro ao realizar login: {}", e.getMessage());
			response.setHeader("error", e.getMessage());
			response.setStatus(HttpStatus.FORBIDDEN.value());
			
			Map<String, String> error = new HashMap<>();
			error.put("error_message", e.getMessage());
			
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			new ObjectMapper().writeValue(response.getOutputStream(), error);
		}
	}
}
