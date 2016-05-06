package br.edu.ifrn.conta.dominio;

import java.math.BigDecimal;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class ValorInicialDoDonoNaContaPatrimonioTests {

    private static final String CARTEIRA = "carteira";
    private static final String PATRIMONIO_INDIVIDUAL = "patrimonio individual";
    private static final String POUPANCA = "poupança";
    
    private static final String PAPAI = "papai";
    private static final String MAMAE = "mamãe";

    private ValorInicialDoDonoNaContaPatrimonio valorInicialDoDonoNaContaPatrimonio(
            String descricaoCategoria, String descricaoDono, BigDecimal valorInicial)
    {
        return ValorInicialDoDonoNaContaPatrimonio.builder().contaPatrimonio(
                ContaPatrimonio.builder().descricao(descricaoCategoria)
                    .categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build()).build())
                .dono(Dono.builder().descricao(descricaoDono).build())
                .valorInicial(valorInicial)
                .build();
    }
    
    public void donoEContaPatrimonioEValorInicialIguais() {        
        assertThat(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, PAPAI, new BigDecimal(100)))
        .isEqualTo(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, PAPAI, new BigDecimal(100)));
    }
    
    public void donoEContaPatrimonioIguaisEValorInicialDiferente() {
        assertThat(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, PAPAI, new BigDecimal(100)))
        .isNotEqualTo(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, PAPAI, new BigDecimal(999)));
    }
    
    public void donoEValorInicialIguaisEContaPatrimonioDiferente() {
        assertThat(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, PAPAI, new BigDecimal(100)))
        .isNotEqualTo(valorInicialDoDonoNaContaPatrimonio(POUPANCA, PAPAI, new BigDecimal(100)));
    }
    
    public void contaPatrimonioEValorInicialIguaisEDonoDiferente() {
        assertThat(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, PAPAI, new BigDecimal(100)))
        .isNotEqualTo(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, MAMAE, new BigDecimal(100)));
    }
    
}
