package br.edu.ifrn.conta.domain;

import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

public class ContaDebitoTests {

	private static final String GASOLINA = "gasolina";
	private static final String TRANSPORTE = "transporte";
	private static final String ONIBUS = "ônibus";
	private static final String AVIAO = "avião";
	private static final String DESPESAS_CORRENTES = "despesasCorrentes";

	@Test
	public void descricaoECategoriaIguais() {
		assertThat(ContaDebito.builder().descricao(GASOLINA)
			.categoria(Categoria.builder().descricao(TRANSPORTE).build())
			.build())
			.isEqualTo(ContaDebito.builder().descricao(GASOLINA)
				.categoria(Categoria.builder().descricao(TRANSPORTE).build())
				.build());
	}

	@Test
	public void descricaoIgualECategoriaDiferente() {
		assertThat(ContaDebito.builder().descricao(GASOLINA)
			.categoria(Categoria.builder().descricao(TRANSPORTE).build())
			.build())
			.isNotEqualTo(ContaDebito.builder().descricao(GASOLINA)
				.categoria(Categoria.builder().descricao(DESPESAS_CORRENTES).build())
				.build());
	}

	@Test
	public void descricaoDiferenteECategoriaIgual() {
		assertThat(ContaDebito.builder().descricao(GASOLINA)
			.categoria(Categoria.builder().descricao(TRANSPORTE).build())
			.build())
			.isNotEqualTo(ContaDebito.builder().descricao(ONIBUS)
				.categoria(Categoria.builder().descricao(TRANSPORTE).build())
				.build());
	}

	@Test
	public void descricaoIgualIgualSemCategoria() {
		NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
			ContaDebito.builder().descricao(GASOLINA).build().equals(
				ContaDebito.builder().descricao(GASOLINA).build());
		});
		assertThat(thrown)
			.isNotNull();
	}

	@Test
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
