/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.edu.ifrn.conta.servico;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.edu.ifrn.conta.dominio.ContaCredito;
import br.edu.ifrn.conta.dominio.ContaDebito;
import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import br.edu.ifrn.conta.dominio.Dono;
import br.edu.ifrn.conta.dominio.Lancamento;
import br.edu.ifrn.conta.persistencia.LancamentoRepository;
import br.edu.ifrn.conta.persistencia.ValorInicialDoDonoNaContaPatrimonioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

/**
 * Servico de Lancamento.
 * @author Marcelo Fernandes
 */
@Component
public class LancamentoServico extends CrudServico<Lancamento, Long> {

	private LancamentoRepository lancamentoRepository;
        private ValorInicialDoDonoNaContaPatrimonioRepository valorInicialDoDonoNaContaPatrimonioRepository;

	@Autowired
	public LancamentoServico(LancamentoRepository lancamentoRepository) {
		super();
		this.lancamentoRepository = lancamentoRepository;
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
