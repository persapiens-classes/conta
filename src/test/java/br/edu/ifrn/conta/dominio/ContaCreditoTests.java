package br.edu.ifrn.conta.dominio;

import java.util.Set;
import java.util.TreeSet;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;

@Test
public class ContaCreditoTests {

    private static final String BOLSA = "bolsa";
    private static final String VENCIMENTO = "vencimento";
    private static final String ESTAGIO = "estágio";
    private static final String RECEITAS_CORRENTES = "receitasCorrentes";
    
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
            .categoria(Categoria.builder().descricao(RECEITAS_CORRENTES).build())
            .build());        
    }
    
    public void descricaoDiferenteECategoriaIgual() {
        assertThat(ContaCredito.builder().descricao(BOLSA)
            .categoria(Categoria.builder().descricao(VENCIMENTO).build())
            .build())
        .isNotEqualTo(ContaCredito.builder().descricao(ESTAGIO)
            .categoria(Categoria.builder().descricao(VENCIMENTO).build())
            .build());        
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void descricaoIgualIgualSemCategoria() {
        assertThat(ContaCredito.builder().descricao(BOLSA).build())
            .isEqualTo(ContaCredito.builder().descricao(BOLSA).build());
    }
    
    public void compareTo() {
        Set<ContaCredito> contasCredito = new TreeSet<>();

        ContaCredito estagio = ContaCredito.builder().descricao(ESTAGIO)
            .categoria(Categoria.builder().descricao(VENCIMENTO).build())
            .build();
        contasCredito.add(estagio);
        ContaCredito bolsa = ContaCredito.builder().descricao(BOLSA)
            .categoria(Categoria.builder().descricao(VENCIMENTO).build())
            .build();
        contasCredito.add(bolsa);
        
        assertThat(contasCredito.iterator().next()).isEqualTo(bolsa);
    }
    
}
