package br.edu.ifrn.conta.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.edu.ifrn.conta.domain.ContaCredito;
import br.edu.ifrn.conta.domain.ContaDebito;
import br.edu.ifrn.conta.domain.ContaPatrimonio;
import br.edu.ifrn.conta.domain.Dono;
import br.edu.ifrn.conta.domain.Lancamento;
import br.edu.ifrn.conta.persistence.LancamentoRepository;
import br.edu.ifrn.conta.persistence.ValorInicialDoDonoNaContaPatrimonioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Service of Lancamento.
 */
@Service
public class LancamentoService extends CrudService<Lancamento, Long> {

	private LancamentoRepository lancamentoRepository;
        private ValorInicialDoDonoNaContaPatrimonioRepository valorInicialDoDonoNaContaPatrimonioRepository;

	@Autowired
	public LancamentoService(LancamentoRepository lancamentoRepository, ValorInicialDoDonoNaContaPatrimonioRepository valorInicialDoDonoNaContaPatrimonioRepository) {
		super();
		this.lancamentoRepository = lancamentoRepository;
                this.valorInicialDoDonoNaContaPatrimonioRepository = valorInicialDoDonoNaContaPatrimonioRepository;
	}

	public BigDecimal saldo(Dono dono, ContaPatrimonio contaPatrimonio) {
            // recupera o valor inicial do dono na conta patrimonio
            BigDecimal result = this.valorInicialDoDonoNaContaPatrimonioRepository
                    .findByDonoAndContaPatrimonio(dono, contaPatrimonio).getValorInicial();

            // soma todos os lancamentos de credito do dono na conta patrimonio
            BigDecimal creditos = this.lancamentoRepository.creditosSum(dono, contaPatrimonio).getValor();

            // subtrai todos os lancamentos de debito do dono na conta patrimonio
            BigDecimal debitos = this.lancamentoRepository.debitosSum(dono, contaPatrimonio).getValor();

            return result.add(creditos).subtract(debitos);
	}

	@Override
	@Transactional
	public Lancamento save(Lancamento objeto) {
		objeto.verificarAtributos();

		return super.save(objeto);
	}

	@Transactional
	public void transferir(BigDecimal valor, Dono donoDebito, ContaDebito contaDebito, ContaPatrimonio contaPatrimonioADebitar, Dono donoCredito, ContaPatrimonio contaPatrimonioACreditar, ContaCredito contaCredito) {

		if (donoCredito.equals(donoDebito)) {
			throw new IllegalArgumentException("Donos das contas devem ser diferentes: "
				+ donoDebito + " = " + donoCredito);
		}

		LocalDateTime data = LocalDateTime.now();

		Lancamento lancamentoComDespesa = Lancamento.builder()
			.contaEntrada(contaDebito)
			.contaSaida(contaPatrimonioADebitar)
			.dono(donoDebito)
			.valor(valor)
			.data(data)
			.descricao("Lançamento de débito de uma transferência")
			.build();
		save(lancamentoComDespesa);

		Lancamento lancamentoComReceita = Lancamento.builder()
			.contaEntrada(contaPatrimonioACreditar)
			.contaSaida(contaCredito)
			.dono(donoCredito)
			.valor(valor)
			.data(data)
			.descricao("Lançamento de crédito de uma transferência")
			.build();
		save(lancamentoComReceita);
	}
}
