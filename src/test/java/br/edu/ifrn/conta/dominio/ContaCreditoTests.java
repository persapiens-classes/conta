package br.edu.ifrn.conta.dominio;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class ContaCreditoTests {

    public void descricaoECategoriaIguais() {
        assertThat(ContaCredito.builder().descricao("bolsa")
            .categoria(Categoria.builder().descricao("vencimento").build())
            .build())
        .isEqualTo(ContaCredito.builder().descricao("bolsa")
            .categoria(Categoria.builder().descricao("vencimento").build())
            .build());
    }
    
    public void descricaoIgualECategoriaDiferente() {
        assertThat(ContaCredito.builder().descricao("bolsa")
            .categoria(Categoria.builder().descricao("vencimento").build())
            .build())
        .isNotEqualTo(ContaCredito.builder().descricao("bolsa")
            .categoria(Categoria.builder().descricao("receitasCorrentes").build())
            .build());        
    }
    
    public void descricaoDiferenteECategoriaIgual() {
        assertThat(ContaCredito.builder().descricao("bolsa")
            .categoria(Categoria.builder().descricao("vencimento").build())
            .build())
        .isNotEqualTo(ContaCredito.builder().descricao("estágio")
            .categoria(Categoria.builder().descricao("vencimento").build())
            .build());        
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void descricaoIgualIgualSemCategoria() {
        assertThat(ContaCredito.builder().descricao("bolsa").build())
            .isEqualTo(ContaCredito.builder().descricao("bolsa").build());
    }
    
}
