package br.com.aliceraltecnologia.jdbcwriterjob.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aliceraltecnologia.jdbcwriterjob.dominio.Cliente;
import br.com.aliceraltecnologia.jdbcwriterjob.dominio.Conta;

@Configuration
public class JdbcCompositeStepConfig {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step jdbcCompositeStep(
			ItemReader<Cliente> jdbcReader, 
			ItemProcessor<Cliente, Conta> geracaoContaProcessor, 
			ClassifierCompositeItemWriter<Conta> classifierContaWriter,
			@Qualifier("clienteValidoFileWriter") FlatFileItemWriter<Conta> clienteValidoFileWriter,
			@Qualifier("clienteInvalidoFileWriter") FlatFileItemWriter<Conta> clienteInvalidoFileWriter) {
		return stepBuilderFactory
				.get("jdbcCompositeStep")
				.<Cliente, Conta>chunk(100)
				.reader(jdbcReader)
				.processor(geracaoContaProcessor)
				.writer(classifierContaWriter)
				.stream(clienteValidoFileWriter)
				.stream(clienteInvalidoFileWriter)
				.build();
	}
}
