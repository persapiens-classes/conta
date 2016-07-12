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
import br.edu.ifrn.conta.dominio.Dono;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = ContaApplication.class)
@WebAppConfiguration
@Test(groups = "dono")
public class DonoRepositoryIT extends AbstractTestNGSpringContextTests {

	@Inject
	private DonoRepository donoRepository;

	@Inject
	private DonoFabrica donoFabrica;

	@BeforeMethod
	void deletarTodos() {
		this.donoRepository.deleteAll();
		assertThat(this.donoRepository.findAll()).isEmpty();
	}

	public void repositorioNaoEhNulo() {
		assertThat(this.donoRepository).isNotNull();
	}

	public void findByDescricao() {
		// cria o ambiente de teste
		Dono papai = this.donoFabrica.papai();
		Dono mamae = this.donoFabrica.mamae();

		// executa a operacao a ser testada
		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.donoRepository.findByDescricao(DonoFabrica.PAPAI)).isEqualTo(papai);
		assertThat(this.donoRepository.findByDescricao(DonoFabrica.MAMAE)).isEqualTo(mamae);
	}

	public void countByDescricao() {
		// cria o ambiente de teste
		this.donoFabrica.papai();
		this.donoFabrica.mamae();

		// executa a operacao a ser testada
		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.donoRepository.countByDescricaoContains("a")).isEqualTo(2);
	}

	public void deleteByDescricao() {
		// cria o ambiente de teste
		this.donoFabrica.papai();

		// executa a operacao a ser testada
		this.donoRepository.deleteByDescricao(DonoFabrica.PAPAI);

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.donoRepository.findAll()).isEmpty();
	}
}
