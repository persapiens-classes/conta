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
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LancamentoTests {

	private static final String PAPAI = "papai";
	private static final String MAMAE = "mamãe";
	private static final String TRANSPORTE = "transporte";
	private static final String PATRIMONIO_INDIVIDUAL = "patrimônio individual";
	private static final String GASOLINA = "gasolina";
	private static final String CARTEIRA = "carteira";
	private static final String DESCRICAO_POSTO_PREDILETO = "comprar gasolina no Posto Predileto";

	private Lancamento lancamento(Date data, BigDecimal valor, String descricaoDono, String descricao) {
		return Lancamento.builder()
			.descricao(descricao)
			.dono(Dono.builder().descricao(descricaoDono).build())
			.valor(valor)
			.data(data)
			.contaEntrada(ContaDebito.builder()
				.descricao(GASOLINA).categoria(
				Categoria.builder().descricao(TRANSPORTE).build()).build())
			.contaSaida(ContaPatrimonio.builder()
				.descricao(CARTEIRA).categoria(
				Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build()).build())
			.build();
	}

	@Test
	public void donoValorDataContaEntradaContaSaidaIguaisComDescricaoDiferente() {
		Date hoje = new Date();

		Lancamento lancamentoGasolina1 = lancamento(hoje, new BigDecimal(100), PAPAI, DESCRICAO_POSTO_PREDILETO);
		Lancamento lancamentoGasolina2 = lancamento(hoje, new BigDecimal(100), PAPAI, "outra descrição qualquer");

		assertThat(lancamentoGasolina1).isEqualTo(lancamentoGasolina2);
	}

	@Test
	public void donoValorDataContaEntradaContaSaidaDescricaoIguaisComValorDiferente() {
		Date hoje = new Date();

		Lancamento lancamentoGasolina1 = lancamento(hoje, new BigDecimal(200), PAPAI, DESCRICAO_POSTO_PREDILETO);
		Lancamento lancamentoGasolina2 = lancamento(hoje, new BigDecimal(100), PAPAI, DESCRICAO_POSTO_PREDILETO);

		assertThat(lancamentoGasolina1).isNotEqualTo(lancamentoGasolina2);
	}

	@Test
	public void compareToComDatasDiferentes() {
		Set<Lancamento> lancamentos = new TreeSet<>();

		Lancamento lancamentoGasolina1 = lancamento(new Date(), new BigDecimal(100), PAPAI, DESCRICAO_POSTO_PREDILETO);
		Lancamento lancamentoGasolina2 = lancamento(new Date(), new BigDecimal(100), PAPAI, DESCRICAO_POSTO_PREDILETO);
		lancamentos.add(lancamentoGasolina2);
		lancamentos.add(lancamentoGasolina1);

		assertThat(lancamentos.iterator().next()).isEqualTo(lancamentoGasolina1);
	}

	@Test
	public void compareToComValoresDiferentes() {
		Date hoje = new Date();

		Set<Lancamento> lancamentos = new TreeSet<>();

		Lancamento lancamentoGasolina1 = lancamento(hoje, new BigDecimal(200), PAPAI, DESCRICAO_POSTO_PREDILETO);
		lancamentos.add(lancamentoGasolina1);
		Lancamento lancamentoGasolina2 = lancamento(hoje, new BigDecimal(100), PAPAI, DESCRICAO_POSTO_PREDILETO);
		lancamentos.add(lancamentoGasolina2);

		assertThat(lancamentos.iterator().next()).isEqualTo(lancamentoGasolina2);
	}

	@Test
	public void compareToComDonosDiferentes() {
		Date hoje = new Date();

		Set<Lancamento> lancamentos = new TreeSet<>();

		Lancamento lancamentoGasolina1 = lancamento(hoje, new BigDecimal(100), PAPAI, DESCRICAO_POSTO_PREDILETO);
		lancamentos.add(lancamentoGasolina1);
		Lancamento lancamentoGasolina2 = lancamento(hoje, new BigDecimal(100), MAMAE, DESCRICAO_POSTO_PREDILETO);
		lancamentos.add(lancamentoGasolina2);

		assertThat(lancamentos.iterator().next()).isEqualTo(lancamentoGasolina2);
	}

}
