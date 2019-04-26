package com.umbrella.cervejaria.model;

public enum Origem {
	NACIONAL("nacional"),
	INTERNACIONAL("internacional");
	
	private String descricao; 
	
	Origem (String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
}
