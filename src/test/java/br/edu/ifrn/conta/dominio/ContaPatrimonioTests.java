package br.edu.ifrn.conta.dominio;

import java.math.BigDecimal;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class ContaPatrimonioTests {

    private static final String CARTEIRA = "carteira";
    private static final String PATRIMONIO_INDIVIDUAL = "patrimonio individual";
    private static final String PATRIMONIO_PESSOAL = "patrimonio pessoal";
    private static final String POUPANCA = "poupança";
    
    public void descricaoECategoriaEValorInicialIguais() {
        assertThat(ContaPatrimonio.builder().descricao(CARTEIRA)
            .categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build())
            .valorInicial(new BigDecimal(100))
            .build())
        .isEqualTo(ContaPatrimonio.builder().descricao(CARTEIRA)
            .categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build())
            .valorInicial(new BigDecimal(100))
            .build());
    }
    
    public void descricaoEValorIgualECategoriaDiferente() {
        assertThat(ContaPatrimonio.builder().descricao(CARTEIRA)
            .categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build())
            .valorInicial(new BigDecimal(100))
            .build())
        .isNotEqualTo(ContaPatrimonio.builder().descricao(CARTEIRA)
            .categoria(Categoria.builder().descricao(PATRIMONIO_PESSOAL).build())
            .valorInicial(new BigDecimal(100))
            .build());        
    }
    
    public void descricaoDiferenteECategoriaIgual() {
        assertThat(ContaPatrimonio.builder().descricao(CARTEIRA)
            .categoria(Categoria.builder().descricao(PATRIMONIO_PESSOAL).build())
            .valorInicial(new BigDecimal(100))
            .build())
        .isNotEqualTo(ContaPatrimonio.builder().descricao(POUPANCA)
            .categoria(Categoria.builder().descricao(PATRIMONIO_PESSOAL).build())
            .valorInicial(new BigDecimal(100))
            .build());        
    }

    public void descricaoECategoriaIguaisEValorInicialDiferente() {
        assertThat(ContaPatrimonio.builder().descricao(POUPANCA)
            .categoria(Categoria.builder().descricao("banco").build())
            .valorInicial(new BigDecimal(100))
            .build())
        .isEqualTo(ContaPatrimonio.builder().descricao(POUPANCA)
            .categoria(Categoria.builder().descricao("banco").build())
            .valorInicial(new BigDecimal(999))
            .build());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void descricaoEValorInicialIguaisSemCategoria() {
        assertThat(ContaPatrimonio.builder().descricao(CARTEIRA)
            .valorInicial(new BigDecimal(100))
            .build())
        .isNotEqualTo(ContaPatrimonio.builder().descricao(POUPANCA)
            .valorInicial(new BigDecimal(100))
            .build());        
    }
    
}
