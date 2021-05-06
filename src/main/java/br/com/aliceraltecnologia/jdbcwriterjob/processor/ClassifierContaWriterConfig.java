package br.com.aliceraltecnologia.jdbcwriterjob.processor;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aliceraltecnologia.jdbcwriterjob.dominio.Conta;

@Configuration
public class ClassifierContaWriterConfig {

	@Bean
	public ClassifierCompositeItemWriter<Conta> classifierContaWriter(
			@Qualifier("clienteInvalidoFileWriter") FlatFileItemWriter<Conta> clienteInvalidoFileWriter,
			CompositeItemWriter<Conta> clienteValidoFileWriter) {
		return new ClassifierCompositeItemWriterBuilder<Conta>()
				.classifier(classifier(clienteInvalidoFileWriter, clienteValidoFileWriter))
				.build();
	}

	@SuppressWarnings("serial")
	private Classifier<Conta, ItemWriter<? super Conta>> classifier(FlatFileItemWriter<Conta> clienteInvalido, CompositeItemWriter<Conta> clienteValido) {
		return new Classifier<Conta, ItemWriter<? super Conta>>() {

			@Override
			public ItemWriter<? super Conta> classify(Conta conta) {
				
				if (conta.getTipo() == null)
					return clienteInvalido;
				else
					return clienteValido;
			}
		
		};
	}
	
}
