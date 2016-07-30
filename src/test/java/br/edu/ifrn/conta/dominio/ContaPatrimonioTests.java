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

import java.math.BigDecimal;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContaPatrimonioTests {

	private static final String CARTEIRA = "carteira";
	private static final String PATRIMONIO_INDIVIDUAL = "patrimonio individual";
	private static final String PATRIMONIO_PESSOAL = "patrimonio pessoal";
	private static final String POUPANCA = "poupança";

	@Test
	public void descricaoECategoriaIguais() {
		assertThat(ContaPatrimonio.builder().descricao(CARTEIRA)
			.categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build())
			.build())
			.isEqualTo(ContaPatrimonio.builder().descricao(CARTEIRA)
				.categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build())
				.build());
	}

	@Test
	public void descricaoECategoriaDiferente() {
		assertThat(ContaPatrimonio.builder().descricao(CARTEIRA)
			.categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build())
			.build())
			.isNotEqualTo(ContaPatrimonio.builder().descricao(CARTEIRA)
				.categoria(Categoria.builder().descricao(PATRIMONIO_PESSOAL).build())
				.build());
	}

	@Test
	public void descricaoDiferenteECategoriaIgual() {
		assertThat(ContaPatrimonio.builder().descricao(CARTEIRA)
			.categoria(Categoria.builder().descricao(PATRIMONIO_PESSOAL).build())
			.build())
			.isNotEqualTo(ContaPatrimonio.builder().descricao(POUPANCA)
				.categoria(Categoria.builder().descricao(PATRIMONIO_PESSOAL).build())
				.build());
	}

	@Test(expected = NullPointerException.class)
	public void descricaoIgualSemCategoria() {
		assertThat(ContaPatrimonio.builder().descricao(CARTEIRA)
			.build())
			.isNotEqualTo(ContaPatrimonio.builder().descricao(POUPANCA)
				.build());
	}

	@Test
	public void descricaoECategoriaIguaisEValorInicialDoDonoDiferente() {

		ValorInicialDoDonoNaContaPatrimonio valorInicialNaCarteira = ValorInicialDoDonoNaContaPatrimonio.builder()
			.dono(Dono.builder().descricao("papai").build())
			.valorInicial(new BigDecimal(100))
			.build();

		ContaPatrimonio carteira1 = ContaPatrimonio.builder().descricao(CARTEIRA)
			.categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build())
			.valorInicialDoDono(valorInicialNaCarteira)
			.build();

		ContaPatrimonio carteira2 = ContaPatrimonio.builder().descricao(CARTEIRA)
			.categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build())
			.build();

		assertThat(carteira1).isEqualTo(carteira2);
	}

}
