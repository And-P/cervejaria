package com.umbrella.cervejeiro.config;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeansException;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.github.mxab.thymeleaf.extras.dataattribute.dialect.DataAttributeDialect;
import com.google.common.cache.CacheBuilder;
import com.umbrella.cervejeiro.controller.CervejasController;
import com.umbrella.cervejeiro.controller.ClientesController;
import com.umbrella.cervejeiro.controller.converter.CidadeConverter;
import com.umbrella.cervejeiro.controller.converter.EstadoConverter;
import com.umbrella.cervejeiro.controller.converter.EstiloConverter;
import com.umbrella.cervejeiro.thymeleaf.BrewerDialect;

import nz.net.ultraq.thymeleaf.LayoutDialect;

	//define que esta é uma classe de configuração do Spring
@Configuration
	//habilita o projeto para desenvolvimento web
@EnableWebMvc 
	//Adiona suporte a algumas coisas que o Spring Data tem, no caso usamos para o Pageable
@EnableSpringDataWebSupport
	//É um array de controllers
@ComponentScan(basePackageClasses = { CervejasController.class, ClientesController.class })
@EnableCaching
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware{
	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		this.applicationContext = applicationContext;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();

			resolver.setTemplateEngine(templateEngine());
			resolver.setCharacterEncoding("UTF-8");
			
		return resolver;
	}
	
	@Bean
	public TemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();

			engine.setEnableSpringELCompiler(true);
			engine.setTemplateResolver(templateResolver());
			engine.addDialect(new LayoutDialect());
			engine.addDialect(new BrewerDialect());
			engine.addDialect(new DataAttributeDialect());

		return engine;
	}
	
	private ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();

			resolver.setApplicationContext(applicationContext);
			resolver.setPrefix("classpath:/templates/");
			resolver.setSuffix(".html");
			resolver.setTemplateMode(TemplateMode.HTML);

		return resolver;
	}
	
	
	@Override //método para adicionar recursos que não têm um Controller
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}
	
	
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		conversionService.addConverter(new EstiloConverter());
		conversionService.addConverter(new CidadeConverter());
		conversionService.addConverter(new EstadoConverter());
		 	
		NumberStyleFormatter bigDecimalFormatter = new NumberStyleFormatter("#,##0.00");
		conversionService.addFormatterForFieldType(BigDecimal.class, bigDecimalFormatter); 
		
		NumberStyleFormatter integerFormatter = new NumberStyleFormatter("#,##0");
		conversionService.addFormatterForFieldType(Integer.class, integerFormatter);
		
		// API de Datas do Java 8
		DateTimeFormatterRegistrar dateTimeFormatter = new DateTimeFormatterRegistrar();
		dateTimeFormatter.setDateFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		dateTimeFormatter.registerFormatters(conversionService);
		
		return conversionService;
	}
	
	@Bean
	public LocaleResolver localeResolver(){
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
	
	@Bean
	public CacheManager cacheManager() {
		CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
				.maximumSize(3)
				.expireAfterAccess(20, TimeUnit.SECONDS);
		
		GuavaCacheManager cacheManager = new GuavaCacheManager();
		cacheManager.setCacheBuilder(cacheBuilder);
		return cacheManager;
	}
}
