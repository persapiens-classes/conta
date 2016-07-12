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
public class ContaCreditoTests {

	private static final String BOLSA = "bolsa";
	private static final String VENCIMENTO = "vencimento";
	private static final String ESTAGIO = "estágio";
	private static final String RECEITAS_CORRENTES = "receitasCorrentes";

	public void descricaoECategoriaIguais() {
		assertThat(ContaCredito.builder().descricao(BOLSA)
			.categoria(Categoria.builder().descricao(VENCIMENTO).build())
			.build())
			.isEqualTo(ContaCredito.builder().descricao(BOLSA)
				.categoria(Categoria.builder().descricao(VENCIMENTO).build())
				.build());
	}

	public void descricaoIgualECategoriaDiferente() {
		assertThat(ContaCredito.builder().descricao(BOLSA)
			.categoria(Categoria.builder().descricao(VENCIMENTO).build())
			.build())
			.isNotEqualTo(ContaCredito.builder().descricao(BOLSA)
				.categoria(Categoria.builder().descricao(RECEITAS_CORRENTES).build())
				.build());
	}

	public void descricaoDiferenteECategoriaIgual() {
		assertThat(ContaCredito.builder().descricao(BOLSA)
			.categoria(Categoria.builder().descricao(VENCIMENTO).build())
			.build())
			.isNotEqualTo(ContaCredito.builder().descricao(ESTAGIO)
				.categoria(Categoria.builder().descricao(VENCIMENTO).build())
				.build());
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void descricaoIgualIgualSemCategoria() {
		assertThat(ContaCredito.builder().descricao(BOLSA).build())
			.isEqualTo(ContaCredito.builder().descricao(BOLSA).build());
	}

	public void compareTo() {
		Set<ContaCredito> contasCredito = new TreeSet<>();

		ContaCredito estagio = ContaCredito.builder().descricao(ESTAGIO)
			.categoria(Categoria.builder().descricao(VENCIMENTO).build())
			.build();
		contasCredito.add(estagio);
		ContaCredito bolsa = ContaCredito.builder().descricao(BOLSA)
			.categoria(Categoria.builder().descricao(VENCIMENTO).build())
			.build();
		contasCredito.add(bolsa);

		assertThat(contasCredito.iterator().next()).isEqualTo(bolsa);
	}

}
