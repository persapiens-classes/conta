package br.edu.ifrn.conta.dominio;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class ContaCreditoTests {

    private static final String BOLSA = "bolsa";
    private static final String VENCIMENTO = "bolsa";
    
    public void descricaoECategoriaIguais() {
        assertThat(ContaCredito.builder().descricao(BOLSA)
            .categoria(Categoria.builder().descricao(VENCIMENTO).build())
            .build())
        .isEqualTo(ContaCredito.builder().descricao(BOLSA)
            .categoria(Categoria.builder().descricao(VENCIMENTO).build())
            .build());
    }
    
    public void descricaoIgualECategoriaDiferente() {
        assertThat(ContaCredito.builder().descricao(BOLSA)
            .categoria(Categoria.builder().descricao(VENCIMENTO).build())
            .build())
        .isNotEqualTo(ContaCredito.builder().descricao(BOLSA)
            .categoria(Categoria.builder().descricao("receitasCorrentes").build())
            .build());        
    }
    
    public void descricaoDiferenteECategoriaIgual() {
        assertThat(ContaCredito.builder().descricao(BOLSA)
            .categoria(Categoria.builder().descricao(VENCIMENTO).build())
            .build())
        .isNotEqualTo(ContaCredito.builder().descricao("estágio")
            .categoria(Categoria.builder().descricao(VENCIMENTO).build())
            .build());        
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void descricaoIgualIgualSemCategoria() {
        assertThat(ContaCredito.builder().descricao(BOLSA).build())
            .isEqualTo(ContaCredito.builder().descricao(BOLSA).build());
    }
    
}
