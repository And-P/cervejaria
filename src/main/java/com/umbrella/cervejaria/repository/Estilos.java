package com.umbrella.cervejaria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umbrella.cervejaria.model.Estilo;
import com.umbrella.cervejaria.repository.helper.estilo.EstilosQueries;

@Repository
public interface Estilos extends JpaRepository<Estilo, Long>, EstilosQueries{
	
	//Gerador de consulta do Spring Data JPA
	public Optional<Estilo> findByNomeIgnoreCase(String nome);
	
}
