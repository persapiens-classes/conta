package br.edu.ifrn.conta.dominio;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class ContaDebitoTests {

    private static final String GASOLINA = "gasolina";    
    private static final String TRANSPORTE = "transporte";    
    
    public void descricaoECategoriaIguais() {
        assertThat(ContaDebito.builder().descricao(GASOLINA)
            .categoria(Categoria.builder().descricao(TRANSPORTE).build())
            .build())
        .isEqualTo(ContaDebito.builder().descricao(GASOLINA)
            .categoria(Categoria.builder().descricao(TRANSPORTE).build())
            .build());
    }
    
    public void descricaoIgualECategoriaDiferente() {
        assertThat(ContaDebito.builder().descricao(GASOLINA)
            .categoria(Categoria.builder().descricao(TRANSPORTE).build())
            .build())
        .isNotEqualTo(ContaDebito.builder().descricao(GASOLINA)
            .categoria(Categoria.builder().descricao("despesasCorrentes").build())
            .build());        
    }
    
    public void descricaoDiferenteECategoriaIgual() {
        assertThat(ContaDebito.builder().descricao(GASOLINA)
            .categoria(Categoria.builder().descricao(TRANSPORTE).build())
            .build())
        .isNotEqualTo(ContaDebito.builder().descricao("ônibus")
            .categoria(Categoria.builder().descricao(TRANSPORTE).build())
            .build());        
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void descricaoIgualIgualSemCategoria() {
        assertThat(ContaDebito.builder().descricao(GASOLINA).build())
            .isEqualTo(ContaDebito.builder().descricao(GASOLINA).build());
    }
    
}
