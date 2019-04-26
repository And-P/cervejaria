package com.umbrella.cervejaria.model;

public enum Sabor {
	ADOCICADA("adocicada"), 
	AMARGA("amarga"),
	FORTE("forte"),
	FRUTADA("frutada"),
	SUAVE("suave");
	
	private String descricao; 
	
	Sabor(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao; 
	}
	
}