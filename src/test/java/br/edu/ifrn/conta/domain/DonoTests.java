package br.edu.ifrn.conta.domain;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

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
