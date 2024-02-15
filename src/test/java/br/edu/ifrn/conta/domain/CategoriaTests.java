package br.edu.ifrn.conta.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoriaTests {

	private static final String TRANSPORTE = "transporte";

	private static final String GASOLINA = "gasolina";

	private static final String IPVA = "ipva";

	private static final String HABITACAO = "habitação";

	@Test
	public void descricaoTransporteIgualSemContas() {
		assertThat(Categoria.builder().descricao(TRANSPORTE).build())
			.isEqualTo(Categoria.builder().descricao(TRANSPORTE).build());
	}

	@Test
	public void descricaoTransporteIgualComContasDiferentes() {
		Categoria categoriaTransporte1 = Categoria.builder().descricao(TRANSPORTE).build();

		Set<Conta> contas = new HashSet<>();
		contas.add(ContaDebito.builder().descricao(GASOLINA).categoria(categoriaTransporte1).build());
		contas.add(ContaDebito.builder().descricao(IPVA).categoria(categoriaTransporte1).build());
		categoriaTransporte1.setContas(contas);

		Categoria categoriaTransporte2 = Categoria.builder().descricao(TRANSPORTE).build();

		assertThat(categoriaTransporte1).isEqualTo(categoriaTransporte2);
	}

	@Test
	public void descricaoDiferente() {
		assertThat(Categoria.builder().descricao(TRANSPORTE).build())
			.isNotEqualTo(Categoria.builder().descricao(HABITACAO).build());
	}

	@Test
	public void compareTo() {
		Set<Categoria> categorias = new TreeSet<>();

		Categoria transporte = Categoria.builder().descricao(TRANSPORTE).build();
		categorias.add(transporte);
		Categoria habitacao = Categoria.builder().descricao(HABITACAO).build();
		categorias.add(habitacao);

		assertThat(categorias.iterator().next()).isEqualTo(habitacao);
	}

}
