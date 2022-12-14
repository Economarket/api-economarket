package br.edu.ifsp.arq.prss6.apieconomarket.utils;

public class UtilsCons {

	public static final String BEARER_ATTRIBUTE_PREFIX = "Bearer ";
	
	public static final String CEP_REGEX = "^(([0-9]{2}\\.[0-9]{3}-[0-9]{3})|([0-9]{2}[0-9]{3}-[0-9]{3})|([0-9]{8}))$";
	
	public static final String HEADER_ATTRIBUTE = "Authorization";
	
	public static final String NON_CHARACTER_REGEX = "\\W|[0-9]";
	
	public static final String ACCENT_CHARS = "ÄÅÁÂÀÃäáâàãÉÊËÈéêëèÍÎÏÌíîïìÖÓÔÒÕöóôòõÜÚÛüúûùÇç";
	
	public static final String NON_ACCENT_CHARS = "AAAAAAaaaaaEEEEeeeeIIIIiiiiOOOOOoooooUUUuuuuCc";
	
	public static final String ROLE_HEADER = "ROLE_";
	
	public static final String ROLE_CLAIM_NAME = "roles";
	
	public static final String USER_DEFAULT_PERMISSION = "USER";
	
	public static final String USER_ID_CLAIM_NAME = "user_id";
	
	public static final String USER_AGENT_HEADER = "User-Agent";
	
	public static final String WHITE_SPACES_REGEX = "\\s";
	
	public static final Integer REFRESH_TO_MINUTES = UtilsFunc.refreshToMinutes();
	
	public static final Integer REFRESH_TO_SECONDS = UtilsFunc.refreshToSeconds();
	
	//TODO: Passar constantes da aplicação para cá
}
