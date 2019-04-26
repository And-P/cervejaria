package com.umbrella.cervejaria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umbrella.cervejaria.model.Cliente;
import com.umbrella.cervejaria.repository.Clientes;
import com.umbrella.cervejaria.service.exception.CpfCnpjClienteJaCadastradoException;

@Service
public class CadastroClienteService {
	
	@Autowired
	private Clientes clientes; 
	
	@Transactional
	public void salvar(Cliente cliente) {
	
		Optional<Cliente> clienteExistente = clientes.findByCpfOuCnpj(cliente.getCpfOuCnpjSemFormatacao());
		
		if(clienteExistente.isPresent()) {
			throw new CpfCnpjClienteJaCadastradoException("CPF/CNPJ já cadastrado");
		}
		
		clientes.save(cliente);
	}
}