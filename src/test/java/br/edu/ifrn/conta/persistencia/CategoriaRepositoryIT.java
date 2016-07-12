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

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = ContaApplication.class)
@WebAppConfiguration
@Test(groups = "categoria")
public class CategoriaRepositoryIT extends AbstractTestNGSpringContextTests {

	@Inject
	private CategoriaRepository categoriaRepository;

	@Inject
	private CategoriaFabrica categoriaFabrica;

	@BeforeMethod
	void deletarTodos() {
		this.categoriaRepository.deleteAll();
		assertThat(this.categoriaRepository.findAll()).isEmpty();
	}

	public void repositorioNaoEhNulo() {
		assertThat(this.categoriaRepository).isNotNull();
	}

	public void deletarUm() {
		// cria o ambiente de teste
		Categoria categoria = this.categoriaFabrica.transporte();

		// executa a operacao a ser testada
		this.categoriaRepository.delete(categoria);

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.categoriaRepository.findOne(categoria.getId())).isNull();
	}

	public void salvarUm() {
		// cria o ambiente de teste
		Categoria categoria = Categoria.builder().descricao(CategoriaFabrica.TRANSPORTE).build();

		// executa a operacao a ser testada
		this.categoriaRepository.save(categoria);

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.categoriaRepository.findAll().iterator().next()).isEqualTo(categoria);
	}

}
