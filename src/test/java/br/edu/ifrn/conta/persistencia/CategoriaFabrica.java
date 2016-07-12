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
import javax.inject.Named;

import br.edu.ifrn.conta.dominio.Categoria;

@Named
public class CategoriaFabrica {

	public final static String SALARIO = "Salário";
	public final static String TRANSPORTE = "Transporte";
	public final static String BANCO = "Banco";
	public final static String CATEGORIA_DESPESA_CONJUGE = "categoria despesa com cônjuge";
	public final static String CATEGORIA_RECEITA_CONJUGE = "categoria receita com cônjuge";

	@Inject
	private CategoriaRepository categoriaRepository;

	private Categoria categoria(String descricao) {
		Categoria categoria = this.categoriaRepository.findByDescricao(descricao);
		if (categoria == null) {
			categoria = Categoria.builder().descricao(descricao).build();
			this.categoriaRepository.save(categoria);
		}
		return categoria;
	}

	public Categoria transporte() {
		return categoria(TRANSPORTE);
	}

	public Categoria banco() {
		return categoria(BANCO);
	}

	public Categoria salario() {
		return categoria(SALARIO);
	}

	public Categoria categoriaReceitaConjuge() {
		return categoria(CATEGORIA_RECEITA_CONJUGE);
	}

	public Categoria categoriaDespesaConjuge() {
		return categoria(CATEGORIA_DESPESA_CONJUGE);
	}
}
