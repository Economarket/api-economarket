package br.edu.ifsp.arq.prss6.apieconomarket.config;

public class JWTParametersConfig {

	//TODO: Passar para um arquivo de configuração
	public static final String TOKEN_SECRET = "0e75640d-65d0-4810-adf5-deaee35fbc3c";
	
	//TODO: Passar para um arquivo de configuração
	public static final int ACCESS_TOKEN_EXPIRATION = 900000; //Token expira a cada 15 min
	
	//TODO: Passar para um arquivo de configuração
	public static final int REFRESH_TOKEN_EXPIRATION = 1800000; //Token expira a cada 30 min
	
	public static int getExpirationTime(TokenTypeEnum tokenType) {
		if(tokenType == TokenTypeEnum.REFRESH_TOKEN) {
			return REFRESH_TOKEN_EXPIRATION;
		}
		
		//Default
		return ACCESS_TOKEN_EXPIRATION;
	}
}
