package br.edu.ifsp.arq.prss6.apieconomarket.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
	
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		JWTAuthFilter jwtAuthFilter = new JWTAuthFilter(authenticationManagerBean());
		jwtAuthFilter.setFilterProcessesUrl(EndpointsConstMapping.AuthEP.LOGIN);
		
		http.csrf().disable()
			.authorizeRequests()
			//Configs de autenticação
			.antMatchers(HttpMethod.GET, "/").permitAll()
			.antMatchers(EndpointsConstMapping.AuthEP.LOGIN).permitAll()
			.antMatchers(EndpointsConstMapping.AuthEP.REFRESH_TOKEN).permitAll()
			
			//Endpoints abertos
			.antMatchers(HttpMethod.POST, EndpointsConstMapping.UserEP.MAIN).permitAll()
			
			//Configs gerais
			.antMatchers(HttpMethod.GET, "/search/market").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.GET, "/search/product").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			
			.antMatchers(HttpMethod.GET, "/user/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.POST, "/user/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.PUT, "/user/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.DELETE, "/user/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			
			.antMatchers(HttpMethod.GET, "/permission/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.POST, "/permission/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.PUT, "/permission/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.DELETE, "/permission/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			
			.antMatchers(HttpMethod.POST, "/register/market").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.PUT, "/register/market").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.DELETE, "/register/market").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
						
//			.anyRequest().denyAll() //DEBUG -> Nega as permissões dos endpoints não configurados
			.anyRequest().hasAuthority("ROLE_ADMIN") 
		.and()
			.addFilter(jwtAuthFilter)
			.addFilterBefore(new JWTValidateFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
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
