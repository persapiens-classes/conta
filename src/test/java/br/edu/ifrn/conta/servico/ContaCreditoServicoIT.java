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

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.ContaCredito;
import br.edu.ifrn.conta.persistencia.CategoriaFabrica;
import br.edu.ifrn.conta.persistencia.ContaCreditoFabrica;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ContaCreditoServicoIT {

	@Autowired
	private ContaCreditoServico contaCreditoServico;

	@Autowired
	private ContaCreditoFabrica contaCreditoFabrica;

	@Autowired
	private CategoriaFabrica categoriaFabrica;

	@Test
	public void repositorioNaoEhNulo() {
		assertThat(this.contaCreditoServico).isNotNull();
	}

	@Test
	public void salvarUm() {
		// cria o ambiente de teste
		ContaCredito contaCredito = this.contaCreditoFabrica.receitaComConjuge();

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.contaCreditoServico.findById(contaCredito.getId()).get())
			.isEqualTo(contaCredito);
	}

	@Test
	public void deletarUm() {
		// cria o ambiente de teste
		ContaCredito contaCredito = this.contaCreditoFabrica.contaCredito(
			"CONTA ÚNICA DO SERVICO IT", this.categoriaFabrica.banco());

		// executa a operacao a ser testada
		this.contaCreditoServico.delete(contaCredito);

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.contaCreditoServico.findById(contaCredito.getId()).isPresent())
			.isFalse();
	}

}
