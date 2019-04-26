package com.umbrella.cervejaria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umbrella.cervejaria.model.Cidade;
import com.umbrella.cervejaria.model.Estado;
import com.umbrella.cervejaria.repository.helper.cidade.CidadesQueries;

public interface Cidades extends JpaRepository<Cidade, Long>, CidadesQueries{
	
	public List<Cidade> findByEstadoCodigo(Long codigoEstado); 
	
	public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);

}
