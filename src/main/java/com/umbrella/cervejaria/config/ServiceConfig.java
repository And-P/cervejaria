package com.umbrella.cervejaria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.umbrella.cervejaria.service.CadastroCervejaService;
import com.umbrella.cervejaria.storage.FotoStorage;
import com.umbrella.cervejaria.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CadastroCervejaService.class)
public class ServiceConfig {
	
	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal();
	}
}
