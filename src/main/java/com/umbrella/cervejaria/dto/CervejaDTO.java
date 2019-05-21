package com.umbrella.cervejaria.dto;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

import com.umbrella.cervejaria.model.Origem;

public class CervejaDTO {

	private Long codigo;
	private String sku;
	private String nome;
	private String origem;
	private BigDecimal valor;
	private String foto;
	
	public CervejaDTO(Long codigo, String sku, String nome, Origem origem, BigDecimal valor, String foto) {

		this.codigo = codigo;
		this.sku = sku;
		this.nome = nome;
		this.origem = origem. getDescricao();
		this.valor = valor;
		this.foto = StringUtils.isEmpty(foto) ? "cerveja-mock.png" : foto;
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getSku() {
		return sku;
	}

	public String getNome() {
		return nome;
	}

	public String getOrigem() {
		return origem;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getFoto() {
		return foto;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	
	
}
