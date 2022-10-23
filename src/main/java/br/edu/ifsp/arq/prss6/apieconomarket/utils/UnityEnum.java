package br.edu.ifsp.arq.prss6.apieconomarket.utils;

import lombok.Getter;

@Getter
public enum UnityEnum {

	CX("Caixa"),
	G("Grama"),
	KG("Kilo"),
	PC("Peça"),
	UN("Unidade");
	
	private String value;
	
	UnityEnum(String value) {
		this.value = value;
	}
}
