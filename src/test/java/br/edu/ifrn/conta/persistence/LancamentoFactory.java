package br.edu.ifrn.conta.persistence;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifrn.conta.domain.Conta;
import br.edu.ifrn.conta.domain.Dono;
import br.edu.ifrn.conta.domain.Lancamento;

@Component
public class LancamentoFactory {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Lancamento lancamento(Dono dono, Conta contaEntrada, Conta contaSaida, BigDecimal valor) {
		Lancamento lancamento = Lancamento.builder()
			.contaEntrada(contaEntrada)
			.contaSaida(contaSaida)
			.data(LocalDateTime.now())
			.dono(dono)
			.valor(valor.setScale(2))
			.build();
		this.lancamentoRepository.save(lancamento);
		return lancamento;
	}

}
