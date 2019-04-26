package com.umbrella.cervejaria.repository.filter;

import java.math.BigDecimal;

public class EstiloFilter {
	
	 
	private String nome;
	private BigDecimal codigo; 
	

	//	Getters & Setters
	public String getNome() {
		return nome;
	}
	public BigDecimal getCodigo() {
		return codigo;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCodigo(BigDecimal codigo) {
		this.codigo = codigo;
	} 
	
}
