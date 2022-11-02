package br.edu.ifsp.arq.prss6.apieconomarket.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.edu.ifsp.arq.prss6.apieconomarket.config.JWTParametersConfig;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Permission;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Product;

public class UtilsFunc {
	
	public static boolean stringEquals(String source, String target) {
		return source.contentEquals(target);
	}
	
	public static boolean stringEqualsIgnoreCase(String source, String target) {
		return source.equalsIgnoreCase(target);
	}

	public static boolean isBlankOrEmpty(String value) {
		return value == null || value.trim().length() == 0;
	}
	
	public static String deleteAllWhiteSpaces(String value) {
		String newValue = 
				value.replaceAll(UtilsCons.WHITE_SPACES_REGEX, "")
				.trim()
				.toLowerCase();

		return newValue;
	}
	
	public static String treatSearchName(String searchName) {
		if(UtilsFunc.isBlankOrEmpty(searchName)) {
			return null;
		}
		
		String processedName = "";
		String aux = searchName.replaceAll(UtilsCons.NON_CHARACTER_REGEX, " ");
		
		for(String s : Arrays.asList(aux.split(" "))) {
			processedName = processedName.concat(s.toLowerCase());
		}
		
		return processedName;
	}
	
	public static List<String> permissionsToRoleList(List<Permission> permissions) {
		return permissions
				.stream()
				.map(p -> "ROLE_".concat(p.getName()))
				.collect(Collectors.toList());
	}
	
	public static Collection<? extends GrantedAuthority> permissionsToAuthoritiesList(
			List<Permission> permissions) {
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		authorities = permissions.stream()
				.map(p -> new SimpleGrantedAuthority(UtilsCons.ROLE_HEADER.concat(p.getName())))
				.collect(Collectors.toList());
		
		return authorities;
	}
	
	public static List<String> authoritiesToRoleList(Collection<? extends GrantedAuthority> authorities) {
		return authorities.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
	}
	
	public static double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
	    double earthRadius = 6371;
	    double dLat = Math.toRadians(lat2 - lat1);
	    double dLng = Math.toRadians(lng2 - lng1);
	    double sindLat = Math.sin(dLat / 2);
	    double sindLng = Math.sin(dLng / 2);
	    double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
	            * Math.cos(Math.toRadians(lat1))
	            * Math.cos(Math.toRadians(lat2));
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double dist = earthRadius * c;
	 
	    return dist * 1000;
	}
	
	public static Integer refreshToSeconds() {
		return JWTParametersConfig.REFRESH_TOKEN_EXPIRATION / 1000;
	}
	
	public static Integer refreshToMinutes() {
		return refreshToSeconds()/60;
	}

	private static Integer levenshtein(String s1, String s2) {
		if(s1.length() < s2.length())
			return levenshtein(s2, s1);

		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
	
		int[] costs = new int[s2.length() + 1];
		
		for (int i = 0; i <= s1.length(); i++) {
		  int lastValue = i;
		  for (int j = 0; j <= s2.length(); j++) {
			if (i == 0) {
				costs[j] = j;
			} else {
			  if (j > 0) {
				int newValue = costs[j - 1];
				if (s1.charAt(i - 1) != s2.charAt(j - 1))
				  newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
				costs[j - 1] = lastValue;
				lastValue = newValue;
			  }
			}
		  }
		  if (i > 0)
			costs[s2.length()] = lastValue;
		}

		return costs[s2.length()];
	}

	private static Double similarity(String s1, String s2){
		double longerLength = (double) Math.max(s1.length(), s2.length());
		if (longerLength == 0 || s1.contains(s2) || s2.contains(s1)) 
			return 1.0;
		return (longerLength - levenshtein(s1, s2))/longerLength;
	}

	public static List<Product> productsBySearch(String search, List<Product> products) {
		double MIN_SIMILARITY = 0.8;
		return products.stream().filter(
			p -> similarity(p.getSearchName(), search) >= MIN_SIMILARITY
		).collect(Collectors.toList());
	}
}
