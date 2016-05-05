package br.edu.ifrn.conta.dominio;

import java.math.BigDecimal;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class ContaPatrimonioTests {

    public void descricaoECategoriaIguais() {
        assertThat(ContaPatrimonio.builder().descricao("carteira")
            .categoria(Categoria.builder().descricao("casa").build())
            .build())
        .isEqualTo(ContaPatrimonio.builder().descricao("carteira")
            .categoria(Categoria.builder().descricao("casa").build())
            .build());
    }
    
    public void descricaoIgualECategoriaDiferente() {
        assertThat(ContaPatrimonio.builder().descricao("carteira")
            .categoria(Categoria.builder().descricao("casa").build())
            .build())
        .isNotEqualTo(ContaPatrimonio.builder().descricao("carteira")
            .categoria(Categoria.builder().descricao("pessoal").build())
            .build());        
    }
    
    public void descricaoDiferenteECategoriaIgual() {
        assertThat(ContaPatrimonio.builder().descricao("carteira")
            .categoria(Categoria.builder().descricao("pessoal").build())
            .build())
        .isNotEqualTo(ContaPatrimonio.builder().descricao("poupança")
            .categoria(Categoria.builder().descricao("pessoal").build())
            .build());        
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void descricaoIgualIgualSemCategoria() {
        assertThat(ContaPatrimonio.builder().descricao("carteira").build())
            .isEqualTo(ContaPatrimonio.builder().descricao("carteira").build());
    }

    public void descricaoECategoriaEValorInicialIguais() {
        assertThat(ContaPatrimonio.builder().descricao("bolsa")
            .categoria(Categoria.builder().descricao("vencimento").build())
            .valorInicial(new BigDecimal(100))
            .build())
        .isEqualTo(ContaPatrimonio.builder().descricao("bolsa")
            .categoria(Categoria.builder().descricao("vencimento").build())
            .valorInicial(new BigDecimal(100))
            .build());
    }

    public void descricaoECategoriaIguaisEValorInicialDiferente() {
        assertThat(ContaPatrimonio.builder().descricao("bolsa")
            .categoria(Categoria.builder().descricao("vencimento").build())
            .valorInicial(new BigDecimal(100))
            .build())
        .isEqualTo(ContaPatrimonio.builder().descricao("bolsa")
            .categoria(Categoria.builder().descricao("vencimento").build())
            .valorInicial(new BigDecimal(999))
            .build());
    }
    
}
