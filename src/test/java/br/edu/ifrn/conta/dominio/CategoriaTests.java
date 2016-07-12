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

package br.edu.ifrn.conta.dominio;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class CategoriaTests {

	private static final String TRANSPORTE = "transporte";
	private static final String GASOLINA = "gasolina";
	private static final String IPVA = "ipva";
	private static final String HABITACAO = "habitação";

	public void descricaoTransporteIgualSemContas() {
		assertThat(Categoria.builder().descricao(TRANSPORTE).build())
			.isEqualTo(Categoria.builder().descricao(TRANSPORTE).build());
	}

	public void descricaoTransporteIgualComContasDiferentes() {
		Categoria categoriaTransporte1 = Categoria.builder().descricao(TRANSPORTE)
			.build();

		Set<Conta> contas = new HashSet<>();
		contas.add(ContaDebito.builder().descricao(GASOLINA).categoria(categoriaTransporte1).build());
		contas.add(ContaDebito.builder().descricao(IPVA).categoria(categoriaTransporte1).build());
		categoriaTransporte1.setContas(contas);

		Categoria categoriaTransporte2 = Categoria.builder().descricao(TRANSPORTE).build();

		assertThat(categoriaTransporte1).isEqualTo(categoriaTransporte2);
	}

	public void descricaoDiferente() {
		assertThat(Categoria.builder().descricao(TRANSPORTE).build())
			.isNotEqualTo(Categoria.builder().descricao(HABITACAO).build());
	}

	public void compareTo() {
		Set<Categoria> categorias = new TreeSet<>();

		Categoria transporte = Categoria.builder().descricao(TRANSPORTE).build();
		categorias.add(transporte);
		Categoria habitacao = Categoria.builder().descricao(HABITACAO).build();
		categorias.add(habitacao);

		assertThat(categorias.iterator().next()).isEqualTo(habitacao);
	}
}
