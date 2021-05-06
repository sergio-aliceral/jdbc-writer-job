package br.com.aliceraltecnologia.jdbcwriterjob.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aliceraltecnologia.jdbcwriterjob.dominio.Cliente;
import br.com.aliceraltecnologia.jdbcwriterjob.dominio.Conta;

@Configuration
public class GeracaoContaProcessorConfig {

	@Bean
	public ItemProcessor<Cliente, Conta> geracaoContaProcessor() {
		return new ClassifierCompositeItemProcessorBuilder<Cliente, Conta>()
				.classifier(new GeracaoContaClassifier())
				.build();
	}
}
