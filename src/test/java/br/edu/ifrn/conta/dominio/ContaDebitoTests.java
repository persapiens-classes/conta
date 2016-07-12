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

import java.util.Set;
import java.util.TreeSet;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class ContaDebitoTests {

	private static final String GASOLINA = "gasolina";
	private static final String TRANSPORTE = "transporte";
	private static final String ONIBUS = "ônibus";
	private static final String AVIAO = "avião";
	private static final String DESPESAS_CORRENTES = "despesasCorrentes";

	public void descricaoECategoriaIguais() {
		assertThat(ContaDebito.builder().descricao(GASOLINA)
			.categoria(Categoria.builder().descricao(TRANSPORTE).build())
			.build())
			.isEqualTo(ContaDebito.builder().descricao(GASOLINA)
				.categoria(Categoria.builder().descricao(TRANSPORTE).build())
				.build());
	}

	public void descricaoIgualECategoriaDiferente() {
		assertThat(ContaDebito.builder().descricao(GASOLINA)
			.categoria(Categoria.builder().descricao(TRANSPORTE).build())
			.build())
			.isNotEqualTo(ContaDebito.builder().descricao(GASOLINA)
				.categoria(Categoria.builder().descricao(DESPESAS_CORRENTES).build())
				.build());
	}

	public void descricaoDiferenteECategoriaIgual() {
		assertThat(ContaDebito.builder().descricao(GASOLINA)
			.categoria(Categoria.builder().descricao(TRANSPORTE).build())
			.build())
			.isNotEqualTo(ContaDebito.builder().descricao(ONIBUS)
				.categoria(Categoria.builder().descricao(TRANSPORTE).build())
				.build());
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void descricaoIgualIgualSemCategoria() {
		assertThat(ContaDebito.builder().descricao(GASOLINA).build())
			.isEqualTo(ContaDebito.builder().descricao(GASOLINA).build());
	}

	public void compareTo() {
		Set<ContaDebito> contasDebito = new TreeSet<>();

		ContaDebito onibus = ContaDebito.builder().descricao(ONIBUS)
			.categoria(Categoria.builder().descricao(TRANSPORTE).build())
			.build();
		contasDebito.add(onibus);
		ContaDebito aviao = ContaDebito.builder().descricao(AVIAO)
			.categoria(Categoria.builder().descricao(TRANSPORTE).build())
			.build();
		contasDebito.add(aviao);
		ContaDebito gasolina = ContaDebito.builder().descricao(GASOLINA)
			.categoria(Categoria.builder().descricao(TRANSPORTE).build())
			.build();
		contasDebito.add(gasolina);

		assertThat(contasDebito.iterator().next()).isEqualTo(aviao);
	}

}
