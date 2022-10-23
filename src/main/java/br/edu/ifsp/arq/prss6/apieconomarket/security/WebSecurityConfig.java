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
import br.edu.ifsp.arq.prss6.apieconomarket.service.RefreshTokenService;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.EndpointsConstMapping;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final UserDetailServiceImpl userService;
	
	private final PasswordEncoder passwordEncoder;
	
	private final RefreshTokenService refreshTokenService;
	
	public WebSecurityConfig(UserDetailServiceImpl userService, PasswordEncoder passwordEncoder, 
			RefreshTokenService refreshTokenService) {
		
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.refreshTokenService = refreshTokenService;
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
		JWTAuthFilter jwtAuthFilter = new JWTAuthFilter(authenticationManagerBean(), refreshTokenService);
		jwtAuthFilter.setFilterProcessesUrl(EndpointsConstMapping.AuthEP.LOGIN);
		
		http.csrf().disable()
			.cors().and()
			.authorizeRequests()
			//Configs de autenticação
			.antMatchers(EndpointsConstMapping.AuthEP.LOGIN).permitAll()
			.antMatchers(EndpointsConstMapping.AuthEP.MAIN + EndpointsConstMapping.AuthEP.REFRESH_TOKEN).permitAll()
			
			//Endpoints abertos
			.antMatchers(HttpMethod.POST, EndpointsConstMapping.UserEP.MAIN).permitAll()
			
			//Configs gerais
			.antMatchers(EndpointsConstMapping.AuthEP.MAIN + 
					EndpointsConstMapping.AuthEP.LOGOUT).hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			
			//BUSCAS
			.antMatchers(HttpMethod.GET, "/search/brand/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.GET, "/search/category/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.GET, "/search/market/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.GET, "/search/product/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			
			//REGISTROS
			.antMatchers(HttpMethod.POST, "/register/brand/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.PUT, "/register/brand/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.DELETE, "/register/brand/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			
			.antMatchers(HttpMethod.POST, "/register/category/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.PUT, "/register/category/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.DELETE, "/register/category/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			
			.antMatchers(HttpMethod.POST, "/register/market/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.PUT, "/register/market/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.DELETE, "/register/market/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			
			.antMatchers(HttpMethod.POST, "/register/product/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.PUT, "/register/product/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.DELETE, "/register/product/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			
			//PERMISSÕES
			.antMatchers(HttpMethod.GET, "/permission/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.POST, "/permission/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.PUT, "/permission/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.DELETE, "/permission/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			
			//USUARIO
			.antMatchers(HttpMethod.GET, "/user/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.POST, "/user/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.PUT, "/user/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.DELETE, "/user/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			
			//UTILS
			.antMatchers(HttpMethod.GET, "/fieldutils/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
						
//			.anyRequest().denyAll() //DEBUG -> Nega as permissões dos endpoints não configurados
			.anyRequest().hasAuthority("ROLE_ADMIN") 
		.and()
			.addFilter(jwtAuthFilter)
			.addFilterBefore(new JWTValidateFilter(authenticationManager(), refreshTokenService), UsernamePasswordAuthenticationFilter.class)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
//		return http.getOrBuild();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
//		corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
//		corsConfiguration.setAllowedOrigins(List.of("*")); //TODO: Setar origens do front-end
		source.registerCorsConfiguration("/**", corsConfiguration);
		
		return source;
	}
}
