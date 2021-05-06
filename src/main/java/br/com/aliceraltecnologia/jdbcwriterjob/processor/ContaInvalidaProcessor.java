package br.com.aliceraltecnologia.jdbcwriterjob.processor;

import org.springframework.batch.item.ItemProcessor;

import br.com.aliceraltecnologia.jdbcwriterjob.dominio.Cliente;
import br.com.aliceraltecnologia.jdbcwriterjob.dominio.Conta;

public class ContaInvalidaProcessor implements ItemProcessor<Cliente, Conta> {

	@Override
	public Conta process(Cliente cliente) throws Exception {
		Conta conta = new Conta();
		conta.setClienteId(cliente.getEmail());
		return conta;
	}
	
}
