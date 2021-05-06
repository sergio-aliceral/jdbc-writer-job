package br.com.aliceraltecnologia.jdbcwriterjob.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aliceraltecnologia.jdbcwriterjob.dominio.Cliente;
import br.com.aliceraltecnologia.jdbcwriterjob.dominio.Conta;

@Configuration
public class JdbcStepConfig {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step jdbcStep(ItemReader<Cliente> jdbcReader, ItemProcessor<Cliente, Conta> geracaoContaProcessor, ItemWriter<Conta> jdbcWriter) {
		return stepBuilderFactory
				.get("jdbcStep")
				.<Cliente, Conta>chunk(100)
				.reader(jdbcReader)
				.processor(geracaoContaProcessor)
				.writer(jdbcWriter)
				.build();
	}
}
