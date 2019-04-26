package com.umbrella.cervejaria.repository.helper.cidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.umbrella.cervejaria.model.Cidade;
import com.umbrella.cervejaria.repository.filter.CidadeFilter;

public interface CidadesQueries {
	
	public Page<Cidade> filtrar(CidadeFilter filtro, Pageable pageable);
	
}
