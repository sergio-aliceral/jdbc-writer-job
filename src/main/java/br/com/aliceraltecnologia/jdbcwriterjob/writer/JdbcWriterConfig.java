package br.com.aliceraltecnologia.jdbcwriterjob.writer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aliceraltecnologia.jdbcwriterjob.dominio.Conta;

@Configuration
public class JdbcWriterConfig {
	
	@Bean
	public JdbcBatchItemWriter<Conta> jdbcWriter(@Qualifier("appDataSource") DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Conta>()
				.dataSource(dataSource)
				.sql("INSERT INTO CONTA(tipo, limite, cliente_id) VALUES(?, ?, ?)")
				.itemPreparedStatementSetter(itemPreparedStatementSetter())
				.build();
	}

	private ItemPreparedStatementSetter<Conta> itemPreparedStatementSetter() {
		return new ItemPreparedStatementSetter<Conta>() {

			@Override
			public void setValues(Conta conta, PreparedStatement ps) throws SQLException {
				
				// Somente para seguir funcionando o Jdbc Job Config
				if (conta.getTipo() == null) {
					ps.setString(1, null);
					ps.setDouble(2, 0);
				} else { 				
					ps.setString(1, conta.getTipo().name());
					ps.setDouble(2, conta.getLimite());
				}
					
				ps.setString(3, conta.getClienteId());
			}
		};
	}
}
