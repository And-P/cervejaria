package com.umbrella.cervejeiro.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.umbrella.cervejeiro.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.umbrella.cervejeiro.thymeleaf.processor.MessageElementTagProcessor;
import com.umbrella.cervejeiro.thymeleaf.processor.OrderElementTagProcessor;
import com.umbrella.cervejeiro.thymeleaf.processor.PaginacaoElementTagProcessor;

public class BrewerDialect extends AbstractProcessorDialect {

	public BrewerDialect() {
		super("Umbrella Brewer", "brewer", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginacaoElementTagProcessor(dialectPrefix));
		return processadores;
	}
} 