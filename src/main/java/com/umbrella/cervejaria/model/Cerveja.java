
package com.umbrella.cervejaria.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.thymeleaf.util.StringUtils;

import com.umbrella.cervejaria.validation.SKU;

@Entity
@Table(name = "cerveja")
public class Cerveja implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Faz o campo ser auto-increment no BD
	private Long codigo; 
	
	@SKU
	@NotBlank(message = "SKU é obrigatório")
	private String sku; 
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(max=30, message="Nome deve ter até 30 caracteres")
	private String nome;
	
	@NotBlank(message = "Descrição é obrigatória")
	@Size(min=1, max=50, message = "Descrições de até 50 caracteres")
	private String descricao; 
	
	@NotNull(message="Valor é obrigatório")
	@DecimalMin("0.50")
	@DecimalMax(value="999.99", message="Valor da cerveja de até R$999,99")
	private BigDecimal valor; 
	
	@NotNull(message="Teor alcoolico obrigatório")
	@DecimalMin("0.01")
	@DecimalMax(value="100.0", message="Teor alcoolico de até 100%")
	@Column(name = "teor_alcoolico")
	private BigDecimal teorAlcoolico;
	
	@NotNull(message="Valor da comissão é obrigatório")
	@DecimalMax(value="100.0", message="Comissão de até 100%")
	private BigDecimal comissao; 
	
	@NotNull(message="Quantidade em estoque obrigatória")
	@Max(value=999, message="Quantidade Estoque de até 999")
	@Column(name = "quantidade_estoque")
	private Integer quantidadeEstoque; 
	
	@NotNull(message="Origem obrigatória")
	@Enumerated(EnumType.STRING)
	private Origem origem; 
	
	@NotNull(message="Sabor obrigatório")
	@Enumerated(EnumType.STRING)
	private Sabor sabor; 
	
	@NotNull(message="Estilo obrigatório")
	@ManyToOne
	@JoinColumn(name = "codigo_estilo")
	private Estilo estilo;
	
	private String foto;

	@Column(name = "content_type")
	private String contentType;
	
	//METODO Q RODA ANTES DA GRAVAÇÃO DO DADO NO BD
	@PrePersist @PreUpdate
	private void prePersistUpdate(){
		sku = sku.toUpperCase();
	}
	
	
	//GETTERS AND SETTERS
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getTeorAlcoolico() {
		return teorAlcoolico;
	}
	public void setTeorAlcoolico(BigDecimal teorAlcoolico) {
		this.teorAlcoolico = teorAlcoolico;
	}
	public BigDecimal getComissao() {
		return comissao;
	}
	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}
	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	public Origem getOrigem() {
		return origem;
	}
	public void setOrigem(Origem origem) {
		this.origem = origem;
	}
	public Sabor getSabor() {
		return sabor;
	}
	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}
	public Estilo getEstilo() {
		return estilo;
	}
	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public String getFotoOuMock() {
		return !StringUtils.isEmpty(foto) ? foto : "cerveja-mock.png";
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	//HashCode e Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cerveja other = (Cerveja) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
