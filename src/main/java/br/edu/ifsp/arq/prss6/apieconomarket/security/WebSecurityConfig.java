package br.edu.ifsp.arq.prss6.apieconomarket.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
			.antMatchers(HttpMethod.GET, "/").permitAll()
			.antMatchers(EndpointsConstMapping.AuthEP.LOGIN).permitAll()
			.antMatchers(EndpointsConstMapping.AuthEP.MAIN + EndpointsConstMapping.AuthEP.REFRESH_TOKEN).permitAll()
			
			//Endpoints abertos
			.antMatchers(HttpMethod.POST, EndpointsConstMapping.UserEP.MAIN).permitAll()
			
			//Configs gerais
			.antMatchers(EndpointsConstMapping.AuthEP.MAIN + 
					EndpointsConstMapping.AuthEP.LOGOUT).hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			
			//BUSCAS
			.antMatchers(HttpMethod.GET, "/search/brand/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.GET, "/search/category/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.GET, "/search/market/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.GET, "/search/product/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			
			//REGISTROS
			.antMatchers(HttpMethod.POST, "/register/brand/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.PUT, "/register/brand/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.DELETE, "/register/brand/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			
			.antMatchers(HttpMethod.POST, "/register/category/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.PUT, "/register/category/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.DELETE, "/register/category/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			
			.antMatchers(HttpMethod.POST, "/register/market/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.PUT, "/register/market/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.DELETE, "/register/market/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			
			.antMatchers(HttpMethod.POST, "/register/product/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.PUT, "/register/product/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.DELETE, "/register/product/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			
			//PERMISSÕES
			.antMatchers(HttpMethod.GET, "/permission/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.POST, "/permission/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.PUT, "/permission/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.DELETE, "/permission/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			
			//USUARIO
			.antMatchers(HttpMethod.GET, "/user/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.POST, "/user/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
			.antMatchers(HttpMethod.PUT, "/user/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.DELETE, "/user/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER") //TODO: Verificar permissão de usuário (não pode deletar outros usuários)
			
			//UTILS
			.antMatchers(HttpMethod.GET, "/fieldutils/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			
			//SHOPPINGLIST
			.antMatchers(HttpMethod.GET, "/shopping/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.POST, "/shopping/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.PUT, "/shopping/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.DELETE, "/shopping/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")	
			
			//PRODUCTLIST
			.antMatchers(HttpMethod.GET, "/productList/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.POST, "/productList/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.PUT, "/productList/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			.antMatchers(HttpMethod.DELETE, "/productList/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER")
			
			//SWAGGER
			.antMatchers("/swagger-ui/**").permitAll()
			.antMatchers("/v2/api-docs").permitAll()
			
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
		
		List<String> allowedMethods = new ArrayList<>();
		allowedMethods.add("GET");
		allowedMethods.add("POST");
		allowedMethods.add("PUT");
		allowedMethods.add("DELETE");
		
		List<String> allowedOrigins = new ArrayList<>();
		allowedOrigins.add("http://localhost:3000");
		allowedOrigins.add("https://localhost:3000");
		allowedOrigins.add("http://127.0.0.1:3000");
		allowedOrigins.add("https://127.0.0.1:3000");
		allowedOrigins.add("http://localhost:8080");
		allowedOrigins.add("https://localhost:8080");
		allowedOrigins.add("http://localhost:4200");
		allowedOrigins.add("https://localhost:4200");
		allowedOrigins.add("http://www.economarket.com.br");
		allowedOrigins.add("https://www.economarket.com.br");
		
		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
		corsConfiguration.setAllowedMethods(allowedMethods);
		corsConfiguration.setAllowedOrigins(allowedOrigins);
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedHeader("Set-Cookie");
		source.registerCorsConfiguration("/**", corsConfiguration);
		
		return source;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("*.html", "/v2/api/docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	}
}
