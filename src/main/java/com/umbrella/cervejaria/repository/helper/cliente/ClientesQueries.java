package com.umbrella.cervejaria.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.umbrella.cervejaria.model.Cliente;
import com.umbrella.cervejaria.repository.filter.ClienteFilter;

public interface ClientesQueries {
	
	public Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable);

}
