package br.edu.ifsp.arq.prss6.apieconomarket.utils;

import java.util.Arrays;

public class UtilsFunc {

	public static boolean isBlankOrEmpty(String value) {
		return value == null || value.isBlank();
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
}
