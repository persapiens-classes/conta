package br.edu.ifrn.conta.dominio;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class ContaDebitoTests {

    public void descricaoECategoriaIguais() {
        assertThat(ContaDebito.builder().descricao("gasolina")
            .categoria(Categoria.builder().descricao("transporte").build())
            .build())
        .isEqualTo(ContaDebito.builder().descricao("gasolina")
            .categoria(Categoria.builder().descricao("transporte").build())
            .build());
    }
    
    public void descricaoIgualECategoriaDiferente() {
        assertThat(ContaDebito.builder().descricao("gasolina")
            .categoria(Categoria.builder().descricao("transporte").build())
            .build())
        .isNotEqualTo(ContaDebito.builder().descricao("gasolina")
            .categoria(Categoria.builder().descricao("despesasCorrentes").build())
            .build());        
    }
    
    public void descricaoDiferenteECategoriaIgual() {
        assertThat(ContaDebito.builder().descricao("gasolina")
            .categoria(Categoria.builder().descricao("transporte").build())
            .build())
        .isNotEqualTo(ContaDebito.builder().descricao("ônibus")
            .categoria(Categoria.builder().descricao("transporte").build())
            .build());        
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void descricaoIgualIgualSemCategoria() {
        assertThat(ContaDebito.builder().descricao("gasolina").build())
            .isEqualTo(ContaDebito.builder().descricao("gasolina").build());
    }
    
}
