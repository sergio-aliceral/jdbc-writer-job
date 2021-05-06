package br.com.aliceraltecnologia.jdbcwriterjob.writer;

import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aliceraltecnologia.jdbcwriterjob.dominio.Conta;

@Configuration
public class CompositeWriterConfig {

	@Bean
	public CompositeItemWriter<Conta> compositeItemWriter(
			@Qualifier("clienteValidoFileWriter") FlatFileItemWriter<Conta> clienteValidoFileWriter, 
			JdbcBatchItemWriter<Conta> jdbcWriter) {
		return new CompositeItemWriterBuilder<Conta>()
				.delegates(clienteValidoFileWriter, jdbcWriter) 
				.build();
	}
	
}
