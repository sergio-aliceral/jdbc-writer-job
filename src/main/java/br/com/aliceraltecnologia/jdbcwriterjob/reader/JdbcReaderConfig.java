package br.com.aliceraltecnologia.jdbcwriterjob.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import br.com.aliceraltecnologia.jdbcwriterjob.dominio.Cliente;

@Configuration
public class JdbcReaderConfig {

	@Bean
	public JdbcPagingItemReader<Cliente> jdbcReader(@Qualifier("appDataSource") DataSource dataSource, PagingQueryProvider queryProvider) {
		return new JdbcPagingItemReaderBuilder<Cliente>()
				.name("jdbcReader")
				.dataSource(dataSource)
				.queryProvider(queryProvider)
				.rowMapper(new BeanPropertyRowMapper<Cliente>(Cliente.class))
				.build();
	}

	@Bean
	public SqlPagingQueryProviderFactoryBean queryProvider(@Qualifier("appDataSource") DataSource dataSource) {
		SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
		queryProvider.setDataSource(dataSource);
		queryProvider.setSelectClause("SELECT *");
		queryProvider.setFromClause("FROM CLIENTE");
		queryProvider.setSortKey("email");
		return queryProvider;
	}
}
