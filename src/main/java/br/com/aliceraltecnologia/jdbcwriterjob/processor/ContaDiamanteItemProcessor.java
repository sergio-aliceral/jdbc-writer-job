package br.com.aliceraltecnologia.jdbcwriterjob.processor;

import org.springframework.batch.item.ItemProcessor;

import br.com.aliceraltecnologia.jdbcwriterjob.dominio.Cliente;
import br.com.aliceraltecnologia.jdbcwriterjob.dominio.Conta;
import br.com.aliceraltecnologia.jdbcwriterjob.dominio.TipoConta;

public class ContaDiamanteItemProcessor implements ItemProcessor<Cliente, Conta> {

	@Override
	public Conta process(Cliente cliente) throws Exception {
		Conta conta = new Conta();
		conta.setClienteId(cliente.getEmail());
		conta.setTipo(TipoConta.DIAMANTE);
		conta.setLimite(5000.0);
		return conta;
	}
	
}
