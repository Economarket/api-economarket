package br.edu.ifsp.arq.prss6.apieconomarket.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.edu.ifsp.arq.prss6.apieconomarket.security.service.UserDetailServiceImpl;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.EndpointsConstMapping;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final UserDetailServiceImpl userService;
	
	private final PasswordEncoder passwordEncoder;
	
	public WebSecurityConfig(UserDetailServiceImpl userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			//Configs de cadastro e login
			.antMatchers(HttpMethod.POST, EndpointsConstMapping.LoginEP.MAIN).permitAll()
			.antMatchers(HttpMethod.POST, EndpointsConstMapping.UserEP.MAIN).permitAll()
			//Configs gerais de ADMIN
			.antMatchers(HttpMethod.GET, "/**").hasAnyAuthority("ROLE_ADMIN")
			.antMatchers(HttpMethod.POST, "/**").hasAnyAuthority("ROLE_ADMIN")
			.antMatchers(HttpMethod.PUT, "/**").hasAnyAuthority("ROLE_ADMIN")
			.antMatchers(HttpMethod.DELETE, "/**").hasAnyAuthority("ROLE_ADMIN")
			//TODO: Configurar outras permissões aqui
			.anyRequest().authenticated()
		.and()
			.addFilter(new JWTAuthFilter(authenticationManager()))
			.addFilter(new JWTValidateFilter(authenticationManager()))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
//		return http.getOrBuild();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", corsConfiguration);
		
		return source;
	}
}
