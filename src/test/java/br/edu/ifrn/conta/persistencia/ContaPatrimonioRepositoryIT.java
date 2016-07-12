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

import javax.inject.Inject;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.Categoria;
import br.edu.ifrn.conta.dominio.ContaPatrimonio;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Example;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = ContaApplication.class)
@WebAppConfiguration
@Test(groups = "contaPatrimonio", dependsOnGroups = "categoria")
public class ContaPatrimonioRepositoryIT extends AbstractTestNGSpringContextTests {

	@Inject
	private ContaPatrimonioFabrica contaPatrimonioFabrica;

	@Inject
	private ContaPatrimonioRepository contaPatrimonioRepository;

	@BeforeMethod
	void deletarTodos() {
		this.contaPatrimonioRepository.deleteAll();
		assertThat(this.contaPatrimonioRepository.findAll()).isEmpty();
	}

	public void repositorioNaoEhNulo() {
		assertThat(this.contaPatrimonioRepository).isNotNull();
	}

	public void findAllByExample() {
		// cria o ambiente de teste
		this.contaPatrimonioFabrica.poupanca();

		ContaPatrimonio contaPatrimonioExemplo = ContaPatrimonio.builder()
			.categoria(Categoria.builder().descricao(CategoriaFabrica.BANCO).build())
			.build();

		// executa a operacao a ser testada
		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.contaPatrimonioRepository.count(Example.of(contaPatrimonioExemplo)))
			.isEqualTo(1);
	}

}
