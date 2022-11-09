package br.edu.ifsp.arq.prss6.apieconomarket.utils;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.User;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket getBean() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.directModelSubstitute(LocalTime.class, String.class)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.edu.ifsp.arq.prss6.apieconomarket"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.genericModelSubstitutes(ResponseEntity.class)
				.ignoredParameterTypes(User.class, Sort.class, Pageable.class, Page.class);
	}
	
	private ApiInfo getInfo() {
		return new ApiInfoBuilder()
				.title("Economarket")
				.description("Economarket Swagger Docs")
				.license("EconoMarket")
				.build();
	}
	
	private ApiKey apiKey() {
        return new ApiKey("JWT", UtilsCons.HEADER_ATTRIBUTE, "header");
    }
	
	private SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }
	
	List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
            = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        
        return Arrays.asList(
            new SecurityReference("JWT", authorizationScopes));
    }
}
