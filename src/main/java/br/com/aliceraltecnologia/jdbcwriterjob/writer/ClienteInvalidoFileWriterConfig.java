package br.com.aliceraltecnologia.jdbcwriterjob.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import br.com.aliceraltecnologia.jdbcwriterjob.dominio.Conta;

@Configuration
public class ClienteInvalidoFileWriterConfig {

	@Bean
	public FlatFileItemWriter<Conta> clienteInvalidoFileWriter() {
		return new FlatFileItemWriterBuilder<Conta>()
				.name("clienteInvalidoFileWriter")
				.resource(new FileSystemResource("./arquivos/contas-invalidas.txt"))
				.delimited()
				.names("clienteId")
				.build();
	}
}
