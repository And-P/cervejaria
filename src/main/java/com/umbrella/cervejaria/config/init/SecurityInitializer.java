package com.umbrella.cervejaria.config.init;

import java.util.EnumSet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.SessionTrackingMode;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		
		//configura para que o JSession que aparecia na URL passa a ser enviado num Cookie
		servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));

		
		FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
		
		characterEncodingFilter.setInitParameter("encoding", "UTF-8");
		characterEncodingFilter.setInitParameter("forceEncoding", "true"); 
		characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
	
		//configura o tempo para expirar a sessão, em segundos
		//servletContext.getSessionCookieConfig().setMaxAge(120);
		
	}

}
