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

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DonoTests {

	private static final String PAPAI = "papai";
	private static final String MAMAE = "mamãe";
	private static final String GASOLINA = "gasolina";
	private static final String IPVA = "ipva";
	private static final String CARTEIRA = "carteira";
	private static final String BANCO = "banco";
	private static final String PATRIMONIO_INDIVIDUAL = "patrimônio individual";

	@Test
	public void descricaoPapaiIgualSemLancamentos() {
		assertThat(Dono.builder().descricao(PAPAI).build())
			.isEqualTo(Dono.builder().descricao(PAPAI).build());
	}

	@Test
	public void descricaoTransporteIgualComLancamentosDiferentes() {
		Lancamento lancamentoGasolina = Lancamento.builder().descricao(GASOLINA).build();
		Lancamento lancamentoIpva = Lancamento.builder().descricao(IPVA).build();

		ValorInicialDoDonoNaContaPatrimonio valorInicialNaCarteira = ValorInicialDoDonoNaContaPatrimonio.builder()
			.contaPatrimonio(ContaPatrimonio.builder().descricao(CARTEIRA)
				.categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build()).build())
			.valorInicial(new BigDecimal(100))
			.build();

		ValorInicialDoDonoNaContaPatrimonio valorInicialNoBanco = ValorInicialDoDonoNaContaPatrimonio.builder()
			.contaPatrimonio(ContaPatrimonio.builder().descricao(BANCO)
				.categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build()).build())
			.valorInicial(new BigDecimal(1000))
			.build();

		Dono papai1 = Dono.builder().descricao(PAPAI)
			.lancamento(lancamentoGasolina)
			.lancamento(lancamentoIpva)
			.valorInicialNaContaPatrimonio(valorInicialNaCarteira)
			.valorInicialNaContaPatrimonio(valorInicialNoBanco)
			.build();

		Dono papai2 = Dono.builder().descricao(PAPAI).build();

		assertThat(papai1).isEqualTo(papai2);
	}

	@Test
	public void descricaoDiferente() {
		assertThat(Dono.builder().descricao(PAPAI).build())
			.isNotEqualTo(Dono.builder().descricao(MAMAE).build());

	}

	@Test
	public void compareTo() {
		Set<Dono> donos = new TreeSet<>();

		Dono papai = Dono.builder().descricao(PAPAI).build();
		donos.add(papai);
		Dono mamae = Dono.builder().descricao(MAMAE).build();
		donos.add(mamae);

		assertThat(donos.iterator().next()).isEqualTo(mamae);
	}

}
