package br.edu.ifsp.arq.prss6.apieconomarket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTParametersConfig {

	public static String TOKEN_SECRET;
	
	public static Integer ACCESS_TOKEN_EXPIRATION;
	
	public static Integer REFRESH_TOKEN_EXPIRATION;
	
	@Value("${application.jwt.secret}")
	private void setTokenSecret(String secret) {
		JWTParametersConfig.TOKEN_SECRET = secret;
	}
	
	@Value("${application.jwt.access-token-expiration}")
	private void setAccessTokenExpiration(Integer accessTokenExpiration) {
		JWTParametersConfig.ACCESS_TOKEN_EXPIRATION = accessTokenExpiration;
	}
	
	@Value("${application.jwt.refresh-token-expiration}")
	private void setRefreshTokenExpiration(Integer refreshTokenExpiration) {
		JWTParametersConfig.REFRESH_TOKEN_EXPIRATION = refreshTokenExpiration;
	}
}
