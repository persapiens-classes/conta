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

package br.edu.ifrn.conta.persistencia;

import java.math.BigDecimal;

import javax.inject.Inject;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.ContaCredito;
import br.edu.ifrn.conta.dominio.ContaDebito;
import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import br.edu.ifrn.conta.dominio.Dono;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = ContaApplication.class)
@WebAppConfiguration
@Test(groups = "lancamento", dependsOnGroups = {"valorInicialDoDonoNaContaPatrimonio", "contaCredito", "contaDebito"})
public class LancamentoRepositoryIT extends AbstractTestNGSpringContextTests {

	@Inject
	private LancamentoFabrica lancamentoFabrica;

	@Inject
	private DonoFabrica donoFabrica;

	@Inject
	private ContaCreditoFabrica contaCreditoFabrica;

	@Inject
	private ContaDebitoFabrica contaDebitoFabrica;

	@Inject
	private ContaPatrimonioFabrica contaPatrimonioFabrica;

	@Inject
	private ValorInicialDoDonoNaContaPatrimonioFabrica valorInicialDoDonoNaContaPatrimonioFabrica;

	@Inject
	private LancamentoRepository lancamentoRepository;

	@BeforeMethod
	void deletarTodos() {
		this.lancamentoRepository.deleteAll();
		assertThat(this.lancamentoRepository.findAll()).isEmpty();
	}

	public void repositorioNaoEhNulo() {
		assertThat(this.lancamentoRepository).isNotNull();
	}

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
		assertThat(this.lancamentoRepository.saldo(papai, poupanca))
			.isEqualTo(new BigDecimal(800).setScale(2));
	}
}
