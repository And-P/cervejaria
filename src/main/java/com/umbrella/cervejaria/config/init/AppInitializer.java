package com.umbrella.cervejaria.config.init;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

//import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.umbrella.cervejaria.config.JPAConfig;
import com.umbrella.cervejaria.config.SecurityConfig;
import com.umbrella.cervejaria.config.ServiceConfig;
import com.umbrella.cervejaria.config.WebConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
	//Faz as configurações raiz do projeto antes do getServletConfigClasses() - que já possui as configs web
	//O que é feito aqui pode ser usado pelo getServletConfigClasses(), o contrário não. 
		return new Class<?>[] {JPAConfig.class, ServiceConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
	// Encaminha o request para onde o Spring vai encontrar a saida para os Controllers
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
	// Recebe a URL request a partir da barra
		return new String[] { "/" };
	}
	
	@Override
	protected Filter[] getServletFilters() {
		
		
		
		//Removido após adicionarmos o Spring Security (19.10) - não resolve mais a acentução
		/*CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
			characterEncodingFilter.setEncoding("UTF-8");
			characterEncodingFilter.setForceEncoding(true);
			return new Filter[] {characterEncodingFilter};*/
		
		return new Filter[] {};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
	
}
