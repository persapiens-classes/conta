package br.edu.ifrn.conta.dominio;

import java.util.Set;
import java.util.TreeSet;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;

@Test
public class ContaDebitoTests {

    private static final String GASOLINA = "gasolina";    
    private static final String TRANSPORTE = "transporte";    
    private static final String ONIBUS = "ônibus";    
    private static final String AVIAO = "avião";    
    private static final String DESPESAS_CORRENTES = "despesasCorrentes";
    
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
            .categoria(Categoria.builder().descricao(DESPESAS_CORRENTES).build())
            .build());        
    }
    
    public void descricaoDiferenteECategoriaIgual() {
        assertThat(ContaDebito.builder().descricao(GASOLINA)
            .categoria(Categoria.builder().descricao(TRANSPORTE).build())
            .build())
        .isNotEqualTo(ContaDebito.builder().descricao(ONIBUS)
            .categoria(Categoria.builder().descricao(TRANSPORTE).build())
            .build());        
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void descricaoIgualIgualSemCategoria() {
        assertThat(ContaDebito.builder().descricao(GASOLINA).build())
            .isEqualTo(ContaDebito.builder().descricao(GASOLINA).build());
    }
    
    public void compareTo() {
        Set<ContaDebito> contasDebito = new TreeSet<>();

        ContaDebito onibus = ContaDebito.builder().descricao(ONIBUS)
            .categoria(Categoria.builder().descricao(TRANSPORTE).build())
            .build();
        contasDebito.add(onibus);
        ContaDebito aviao = ContaDebito.builder().descricao(AVIAO)
            .categoria(Categoria.builder().descricao(TRANSPORTE).build())
            .build();
        contasDebito.add(aviao);
        ContaDebito gasolina = ContaDebito.builder().descricao(GASOLINA)
            .categoria(Categoria.builder().descricao(TRANSPORTE).build())
            .build();
        contasDebito.add(gasolina);
        
        assertThat(contasDebito.iterator().next()).isEqualTo(aviao);
    }
    
}
