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

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.ContaCredito;
import br.edu.ifrn.conta.dominio.ContaDebito;
import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import br.edu.ifrn.conta.dominio.Dono;
import br.edu.ifrn.conta.dominio.Lancamento;
import br.edu.ifrn.conta.persistencia.ContaCreditoFabrica;
import br.edu.ifrn.conta.persistencia.ContaDebitoFabrica;
import br.edu.ifrn.conta.persistencia.ContaPatrimonioFabrica;
import br.edu.ifrn.conta.persistencia.DonoFabrica;
import br.edu.ifrn.conta.persistencia.LancamentoFabrica;
import br.edu.ifrn.conta.persistencia.ValorInicialDoDonoNaContaPatrimonioFabrica;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class LancamentoServicoIT {

	@Autowired
	private LancamentoFabrica lancamentoFabrica;

	@Autowired
	private DonoFabrica donoFabrica;

	@Autowired
	private LancamentoServico lancamentoServico;

	@Autowired
	private ValorInicialDoDonoNaContaPatrimonioFabrica valorInicialDoDonoNaContaPatrimonioFabrica;

	@Autowired
	private ContaPatrimonioFabrica contaPatrimonioFabrica;

	@Autowired
	private ContaCreditoFabrica contaCreditoFabrica;

	@Autowired
	private ContaDebitoFabrica contaDebitoFabrica;

	@BeforeEach
	public void deletarTodos() {
		this.lancamentoServico.deleteAll();
		assertThat(this.lancamentoServico.findAll())
			.isEmpty();
	}

	@Test
	public void repositorioNaoEhNulo() {
		assertThat(this.lancamentoServico)
			.isNotNull();
	}

	@Test
	public void lancamentoComContaEntradaInvalida() {
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Lancamento lancamento = Lancamento.builder()
			.contaEntrada(this.contaCreditoFabrica.estagio())
			.contaSaida(this.contaPatrimonioFabrica.poupanca())
			.valor(BigDecimal.TEN)
			.data(LocalDateTime.now())
			.dono(this.donoFabrica.papai())
			.build();

			this.lancamentoServico.save(lancamento);
		});
		assertThat(thrown)
			.isNotNull();
	}

	@Test
	public void lancamentoComContaSaidaInvalida() {
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Lancamento lancamento = Lancamento.builder()
			.contaEntrada(this.contaPatrimonioFabrica.poupanca())
			.contaSaida(this.contaDebitoFabrica.gasolina())
			.valor(BigDecimal.TEN)
			.data(LocalDateTime.now())
			.dono(this.donoFabrica.papai())
			.build();

			this.lancamentoServico.save(lancamento);
		});
		assertThat(thrown)
			.isNotNull();
	}

	@Test
	public void transferenciaDePapaiParaMamae() {
		this.lancamentoServico.transferir(BigDecimal.TEN, this.donoFabrica.papai(),
			this.contaDebitoFabrica.despesaComConjuge(), this.contaPatrimonioFabrica.poupanca(),
			this.donoFabrica.mamae(), this.contaPatrimonioFabrica.poupanca(),
			this.contaCreditoFabrica.receitaComConjuge());
	}

	@Test
	public void saldo800() {
		// cria o ambiente de teste
		Dono papai = this.donoFabrica.papai();

		ContaPatrimonio poupanca
			= this.contaPatrimonioFabrica.poupanca();

		ContaDebito gasolina
			= this.contaDebitoFabrica.gasolina();

		ContaCredito estagio
			= this.contaCreditoFabrica.estagio();

		this.valorInicialDoDonoNaContaPatrimonioFabrica.valorInicialDoDonoNaContaPatrimonio(
			papai, poupanca, new BigDecimal(1000));

		this.lancamentoFabrica.lancamento(papai, gasolina, poupanca, new BigDecimal(500));
		this.lancamentoFabrica.lancamento(papai, poupanca, estagio, new BigDecimal(300));

		// executa a operacao a ser testada
		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.lancamentoServico.saldo(papai, poupanca))
			.isEqualTo(new BigDecimal(800).setScale(2));
	}
}
