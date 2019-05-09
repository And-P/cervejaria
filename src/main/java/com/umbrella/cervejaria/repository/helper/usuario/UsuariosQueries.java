package com.umbrella.cervejaria.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import com.umbrella.cervejaria.model.Usuario;
import com.umbrella.cervejaria.repository.filter.UsuarioFilter;

public interface UsuariosQueries {

	public Optional<Usuario> porEmailEAtivo(String email);
	
	public List<String> permissoes(Usuario usuario);
	
	public List<Usuario> filtrar(UsuarioFilter filtro);
	
}
