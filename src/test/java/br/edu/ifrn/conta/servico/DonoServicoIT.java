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
import br.edu.ifrn.conta.dominio.Dono;
import br.edu.ifrn.conta.persistencia.DonoFabrica;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = ContaApplication.class)
@WebAppConfiguration
@Test(groups = "dono")
public class DonoServicoIT extends AbstractTestNGSpringContextTests {

	@Inject
	private DonoServico donoServico;

	public void repositorioNaoEhNulo() {
		assertThat(this.donoServico).isNotNull();
	}

	@BeforeMethod
	void deletarTodos() {
		this.donoServico.deleteAll();
		assertThat(this.donoServico.findAll()).isEmpty();
	}

	public void salvarUm() {
		// cria o ambiente de teste
		Dono dono = Dono.builder().descricao(DonoFabrica.PAPAI).build();

		// executa a operacao a ser testada
		this.donoServico.save(dono);

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.donoServico.findAll().iterator().next()).isEqualTo(dono);
	}

	public void deletarUm() {
		// cria o ambiente de teste
		Dono dono = Dono.builder().descricao(DonoFabrica.PAPAI).build();
		this.donoServico.save(dono);

		// executa a operacao a ser testada
		this.donoServico.delete(dono);

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.donoServico.findAll().iterator().hasNext()).isFalse();
	}

}
