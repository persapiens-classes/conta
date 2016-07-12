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

import javax.inject.Inject;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.ContaCredito;
import br.edu.ifrn.conta.persistencia.CategoriaFabrica;
import br.edu.ifrn.conta.persistencia.ContaCreditoFabrica;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = ContaApplication.class)
@WebAppConfiguration
@Test(groups = "contaCredito", dependsOnGroups = "categoria")
public class ContaCreditoServicoIT extends AbstractTestNGSpringContextTests {

	@Inject
	private ContaCreditoServico contaCreditoServico;

	@Inject
	private ContaCreditoFabrica contaCreditoFabrica;

	@Inject
	private CategoriaFabrica categoriaFabrica;

	@BeforeMethod
	void deletarTodos() {
		this.contaCreditoServico.deleteAll();
		assertThat(this.contaCreditoServico.findAll()).isEmpty();
	}

	public void repositorioNaoEhNulo() {
		assertThat(this.contaCreditoServico).isNotNull();
	}

	public void salvarUm() {
		// cria o ambiente de teste
		ContaCredito contaCredito = ContaCredito.builder()
			.descricao(ContaCreditoFabrica.ESTAGIO)
			.categoria(this.categoriaFabrica.salario())
			.build();

		// executa a operacao a ser testada
		this.contaCreditoServico.save(contaCredito);

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.contaCreditoServico.findAll().iterator().next()).isEqualTo(contaCredito);
	}

	public void deletarUm() {
		// cria o ambiente de teste
		ContaCredito contaCredito = ContaCredito.builder()
			.descricao(ContaCreditoFabrica.ESTAGIO)
			.categoria(this.categoriaFabrica.salario())
			.build();
		this.contaCreditoServico.save(contaCredito);

		// executa a operacao a ser testada
		this.contaCreditoServico.delete(contaCredito);

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.contaCreditoServico.findAll().iterator().hasNext()).isFalse();
	}

}
