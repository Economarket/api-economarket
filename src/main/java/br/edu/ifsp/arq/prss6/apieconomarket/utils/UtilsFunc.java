package br.edu.ifsp.arq.prss6.apieconomarket.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Permission;

public class UtilsFunc {

	public static boolean isBlankOrEmpty(String value) {
		return value == null || value.isBlank();
	}
	
	public static String deleteAllWhiteSpaces(String value) {
		String newValue = 
				value.replaceAll(UtilsCons.WHITE_SPACES_REGEX, "")
				.trim()
				.toLowerCase();

		return newValue;
	}
	
	public static String removeWhiteSpacesIfExists(String value) {
		if(UtilsFunc.isBlankOrEmpty(value)) {
			return null;
		}
		
		String processedName = "";
		for(String s : Arrays.asList(value.split(" "))) {
			processedName = processedName.concat(s);
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
}
