package br.edu.ifrn.conta.domain;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

public class ContaPatrimonioTests {

    private static final String CARTEIRA = "carteira";
    private static final String PATRIMONIO_INDIVIDUAL = "patrimonio individual";
    private static final String PATRIMONIO_PESSOAL = "patrimonio pessoal";
    private static final String POUPANCA = "poupanca";

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

    @Test
    public void descricaoIgualSemCategoria() {
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            ContaPatrimonio.builder().descricao(CARTEIRA).build().equals(
                    ContaPatrimonio.builder().descricao(POUPANCA).build());
        });
        assertThat(thrown)
                .isNotNull();
    }

    @Test
    public void descricaoECategoriaIguaisEValorInicialDoDonoDiferente() {

        ValorInicialDoDonoNaContaPatrimonio valorInicialNaCarteira = ValorInicialDoDonoNaContaPatrimonio.builder()
                .dono(Dono.builder().descricao("papai").build())
                .valorInicial(new BigDecimal(100))
                .build();

        ContaPatrimonio carteira1 = ContaPatrimonio.builder().descricao(CARTEIRA)
                .categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build())
                .valoresIniciaisDosDono(valorInicialNaCarteira)
                .build();

        ContaPatrimonio carteira2 = ContaPatrimonio.builder().descricao(CARTEIRA)
                .categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build())
                .build();

        assertThat(carteira1).isEqualTo(carteira2);
    }

}
