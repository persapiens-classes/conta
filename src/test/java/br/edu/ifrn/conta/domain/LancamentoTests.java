package br.edu.ifrn.conta.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LancamentoTests {

	private static final String PAPAI = "papai";

	private static final String MAMAE = "mamae";

	private static final String TRANSPORTE = "transporte";

	private static final String PATRIMONIO_INDIVIDUAL = "patrimônio individual";

	private static final String GASOLINA = "gasolina";

	private static final String CARTEIRA = "carteira";

	private static final String DESCRICAO_POSTO_PREDILETO = "comprar gasolina no Posto Predileto";

	private Lancamento lancamento(LocalDateTime data, BigDecimal valor, String descricaoDono, String descricao) {
		return Lancamento.builder()
			.descricao(descricao)
			.dono(Dono.builder().descricao(descricaoDono).build())
			.valor(valor)
			.data(data)
			.contaEntrada(ContaDebito.builder()
				.descricao(GASOLINA)
				.categoria(Categoria.builder().descricao(TRANSPORTE).build())
				.build())
			.contaSaida(ContaPatrimonio.builder()
				.descricao(CARTEIRA)
				.categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build())
				.build())
			.build();
	}

	@Test
	public void donoValorDataContaEntradaContaSaidaIguaisComDescricaoDiferente() {
		LocalDateTime hoje = LocalDateTime.now();

		Lancamento lancamentoGasolina1 = lancamento(hoje, new BigDecimal(100), PAPAI, DESCRICAO_POSTO_PREDILETO);
		Lancamento lancamentoGasolina2 = lancamento(hoje, new BigDecimal(100), PAPAI, "outra descrição qualquer");

		assertThat(lancamentoGasolina1).isEqualTo(lancamentoGasolina2);
	}

	@Test
	public void donoValorDataContaEntradaContaSaidaDescricaoIguaisComValorDiferente() {
		LocalDateTime hoje = LocalDateTime.now();

		Lancamento lancamentoGasolina1 = lancamento(hoje, new BigDecimal(200), PAPAI, DESCRICAO_POSTO_PREDILETO);
		Lancamento lancamentoGasolina2 = lancamento(hoje, new BigDecimal(100), PAPAI, DESCRICAO_POSTO_PREDILETO);

		assertThat(lancamentoGasolina1).isNotEqualTo(lancamentoGasolina2);
	}

	@Test
	public void compareToComDatasDiferentes() {
		Set<Lancamento> lancamentos = new TreeSet<>();

		Lancamento lancamentoGasolina1 = lancamento(LocalDateTime.now(), new BigDecimal(100), PAPAI,
				DESCRICAO_POSTO_PREDILETO);
		Lancamento lancamentoGasolina2 = lancamento(LocalDateTime.now(), new BigDecimal(100), PAPAI,
				DESCRICAO_POSTO_PREDILETO);
		lancamentos.add(lancamentoGasolina2);
		lancamentos.add(lancamentoGasolina1);

		assertThat(lancamentos.iterator().next()).isEqualTo(lancamentoGasolina1);
	}

	@Test
	public void compareToComValoresDiferentes() {
		LocalDateTime hoje = LocalDateTime.now();

		Set<Lancamento> lancamentos = new TreeSet<>();

		Lancamento lancamentoGasolina1 = lancamento(hoje, new BigDecimal(200), PAPAI, DESCRICAO_POSTO_PREDILETO);
		lancamentos.add(lancamentoGasolina1);
		Lancamento lancamentoGasolina2 = lancamento(hoje, new BigDecimal(100), PAPAI, DESCRICAO_POSTO_PREDILETO);
		lancamentos.add(lancamentoGasolina2);

		assertThat(lancamentos.iterator().next()).isEqualTo(lancamentoGasolina2);
	}

	@Test
	public void compareToComDonosDiferentes() {
		LocalDateTime hoje = LocalDateTime.now();

		Set<Lancamento> lancamentos = new TreeSet<>();

		Lancamento lancamentoGasolina1 = lancamento(hoje, new BigDecimal(100), PAPAI, DESCRICAO_POSTO_PREDILETO);
		lancamentos.add(lancamentoGasolina1);
		Lancamento lancamentoGasolina2 = lancamento(hoje, new BigDecimal(100), MAMAE, DESCRICAO_POSTO_PREDILETO);
		lancamentos.add(lancamentoGasolina2);

		assertThat(lancamentos.iterator().next()).isEqualTo(lancamentoGasolina2);
	}

}
