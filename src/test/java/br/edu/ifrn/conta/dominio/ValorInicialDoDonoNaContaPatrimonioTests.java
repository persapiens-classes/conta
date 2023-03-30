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
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValorInicialDoDonoNaContaPatrimonioTests {

	private static final String CARTEIRA = "carteira";
	private static final String PATRIMONIO_INDIVIDUAL = "patrimonio individual";
	private static final String POUPANCA = "poupança";

	private static final String PAPAI = "papai";
	private static final String MAMAE = "mamãe";

	private ValorInicialDoDonoNaContaPatrimonio valorInicialDoDonoNaContaPatrimonio(
		String descricaoCategoria, String descricaoDono, BigDecimal valorInicial) {
		return ValorInicialDoDonoNaContaPatrimonio.builder().contaPatrimonio(
			ContaPatrimonio.builder().descricao(descricaoCategoria)
			.categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build()).build())
			.dono(Dono.builder().descricao(descricaoDono).build())
			.valorInicial(valorInicial)
			.build();
	}

	@Test
	public void donoEContaPatrimonioEValorInicialIguais() {
		assertThat(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, PAPAI, new BigDecimal(100)))
			.isEqualTo(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, PAPAI, new BigDecimal(100)));
	}

	@Test
	public void donoEContaPatrimonioIguaisEValorInicialDiferente() {
		assertThat(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, PAPAI, new BigDecimal(100)))
			.isNotEqualTo(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, PAPAI, new BigDecimal(999)));
	}

	@Test
	public void donoEValorInicialIguaisEContaPatrimonioDiferente() {
		assertThat(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, PAPAI, new BigDecimal(100)))
			.isNotEqualTo(valorInicialDoDonoNaContaPatrimonio(POUPANCA, PAPAI, new BigDecimal(100)));
	}

	@Test
	public void contaPatrimonioEValorInicialIguaisEDonoDiferente() {
		assertThat(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, PAPAI, new BigDecimal(100)))
			.isNotEqualTo(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, MAMAE, new BigDecimal(100)));
	}

	private ValorInicialDoDonoNaContaPatrimonio valorInicialDoDonoNaContaPatrimonio(
		BigDecimal valorInicial, String descricaoDono, String descricaoConta) {
		return ValorInicialDoDonoNaContaPatrimonio.builder()
			.valorInicial(valorInicial)
			.dono(Dono.builder().descricao(descricaoDono).build())
			.contaPatrimonio(ContaPatrimonio.builder()
				.descricao(descricaoConta)
				.categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build()).build())
			.build();
	}

	@Test
	public void compareToComDonosDiferentes() {
		Set<ValorInicialDoDonoNaContaPatrimonio> valoresIniciais = new TreeSet<>();

		ValorInicialDoDonoNaContaPatrimonio valorInicialCarteiraPapai = valorInicialDoDonoNaContaPatrimonio(
			new BigDecimal(100), PAPAI, CARTEIRA);
		valoresIniciais.add(valorInicialCarteiraPapai);

		ValorInicialDoDonoNaContaPatrimonio valorInicialCarteiraMamae = valorInicialDoDonoNaContaPatrimonio(
			new BigDecimal(100), MAMAE, CARTEIRA);
		valoresIniciais.add(valorInicialCarteiraMamae);

		assertThat(valoresIniciais.iterator().next()).isEqualTo(valorInicialCarteiraMamae);
	}

	@Test
	public void compareToComValoresIniciaisDiferentes() {
		Set<ValorInicialDoDonoNaContaPatrimonio> valoresIniciais = new TreeSet<>();

		ValorInicialDoDonoNaContaPatrimonio valorInicial999 = valorInicialDoDonoNaContaPatrimonio(
			new BigDecimal(999), PAPAI, CARTEIRA);
		valoresIniciais.add(valorInicial999);

		ValorInicialDoDonoNaContaPatrimonio valorInicial100 = valorInicialDoDonoNaContaPatrimonio(
			new BigDecimal(100), PAPAI, CARTEIRA);
		valoresIniciais.add(valorInicial100);

		assertThat(valoresIniciais.iterator().next()).isEqualTo(valorInicial100);
	}

	@Test
	public void compareToComContaPatrimonioDiferentes() {
		Set<ValorInicialDoDonoNaContaPatrimonio> valoresIniciais = new TreeSet<>();

		ValorInicialDoDonoNaContaPatrimonio valorInicialPoupanca = valorInicialDoDonoNaContaPatrimonio(
			new BigDecimal(100), PAPAI, POUPANCA);
		valoresIniciais.add(valorInicialPoupanca);

		ValorInicialDoDonoNaContaPatrimonio valorInicialCarteira = valorInicialDoDonoNaContaPatrimonio(
			new BigDecimal(100), PAPAI, CARTEIRA);
		valoresIniciais.add(valorInicialCarteira);

		assertThat(valoresIniciais.iterator().next()).isEqualTo(valorInicialCarteira);
	}

}
