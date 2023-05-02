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
import java.util.Date;

import jakarta.inject.Inject;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.Lancamento;
import br.edu.ifrn.conta.persistencia.ContaCreditoFabrica;
import br.edu.ifrn.conta.persistencia.ContaDebitoFabrica;
import br.edu.ifrn.conta.persistencia.ContaPatrimonioFabrica;
import br.edu.ifrn.conta.persistencia.DonoFabrica;
import br.edu.ifrn.conta.persistencia.LancamentoFabrica;
import br.edu.ifrn.conta.persistencia.ValorInicialDoDonoNaContaPatrimonioFabrica;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class LancamentoServicoIT {

	@Inject
	private LancamentoFabrica lancamentoFabrica;

	@Inject
	private DonoFabrica donoFabrica;

	@Inject
	private LancamentoServico lancamentoServico;

	@Inject
	private ValorInicialDoDonoNaContaPatrimonioFabrica valorInicialDoDonoNaContaPatrimonioFabrica;

	@Inject
	private ContaPatrimonioFabrica contaPatrimonioFabrica;

	@Inject
	private ContaCreditoFabrica contaCreditoFabrica;

	@Inject
	private ContaDebitoFabrica contaDebitoFabrica;

	@BeforeAll
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
			.data(new Date())
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
			.data(new Date())
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
}
