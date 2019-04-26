package com.umbrella.cervejaria.repository.helper.cerveja;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.umbrella.cervejaria.model.Cerveja;
import com.umbrella.cervejaria.repository.filter.CervejaFilter;

public interface CervejasQueries{
	
	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);
	
}
