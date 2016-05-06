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
    
    public void descricaoECategoriaIguais() {
        assertThat(ContaPatrimonio.builder().descricao(CARTEIRA)
            .categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build())
            .build())
        .isEqualTo(ContaPatrimonio.builder().descricao(CARTEIRA)
            .categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build())
            .build());
    }
    
    public void descricaoECategoriaDiferente() {
        assertThat(ContaPatrimonio.builder().descricao(CARTEIRA)
            .categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build())
            .build())
        .isNotEqualTo(ContaPatrimonio.builder().descricao(CARTEIRA)
            .categoria(Categoria.builder().descricao(PATRIMONIO_PESSOAL).build())
            .build());        
    }
    
    public void descricaoDiferenteECategoriaIgual() {
        assertThat(ContaPatrimonio.builder().descricao(CARTEIRA)
            .categoria(Categoria.builder().descricao(PATRIMONIO_PESSOAL).build())
            .build())
        .isNotEqualTo(ContaPatrimonio.builder().descricao(POUPANCA)
            .categoria(Categoria.builder().descricao(PATRIMONIO_PESSOAL).build())
            .build());        
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void descricaoIgualSemCategoria() {
        assertThat(ContaPatrimonio.builder().descricao(CARTEIRA)
            .build())
        .isNotEqualTo(ContaPatrimonio.builder().descricao(POUPANCA)
            .build());        
    }
    
    public void descricaoECategoriaIguaisEValorInicialDoDonoDiferente() {
        
        ValorInicialDoDonoNaContaPatrimonio valorInicialNaCarteira = ValorInicialDoDonoNaContaPatrimonio.builder()
            .dono(Dono.builder().descricao("papai").build())
            .valorInicial(new BigDecimal(100))
            .build();        
        
        ContaPatrimonio carteira1 = ContaPatrimonio.builder().descricao(CARTEIRA)
            .categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build())
            .valorInicialDoDono(valorInicialNaCarteira)
            .build();        
        
        ContaPatrimonio carteira2 = ContaPatrimonio.builder().descricao(CARTEIRA)
            .categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build())
            .build();        
        
        assertThat(carteira1).isEqualTo(carteira2);
    }
    
}
