package br.edu.ifrn.conta.domain;

import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

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

	@Test
	public void descricaoIgualECategoriaDiferente() {
		assertThat(ContaCredito.builder().descricao(BOLSA)
			.categoria(Categoria.builder().descricao(VENCIMENTO).build())
			.build())
			.isNotEqualTo(ContaCredito.builder().descricao(BOLSA)
				.categoria(Categoria.builder().descricao(RECEITAS_CORRENTES).build())
				.build());
	}

	@Test
	public void descricaoDiferenteECategoriaIgual() {
		assertThat(ContaCredito.builder().descricao(BOLSA)
			.categoria(Categoria.builder().descricao(VENCIMENTO).build())
			.build())
			.isNotEqualTo(ContaCredito.builder().descricao(ESTAGIO)
				.categoria(Categoria.builder().descricao(VENCIMENTO).build())
				.build());
	}

	@Test
	public void descricaoIgualIgualSemCategoria() {
		NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
			ContaCredito.builder().descricao(BOLSA).build().equals(
				ContaCredito.builder().descricao(BOLSA).build());
		});
		assertThat(thrown)
			.isNotNull();
	}

	@Test
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
