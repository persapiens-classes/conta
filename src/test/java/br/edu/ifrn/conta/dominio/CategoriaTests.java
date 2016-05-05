package br.edu.ifrn.conta.dominio;

import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;

@Test
public class CategoriaTests {

    private static final String TRANSPORTE = "transporte";
    
    public void descricaoTransporteIgualSemContas() {
        assertThat(Categoria.builder().descricao(TRANSPORTE).build())
            .isEqualTo(Categoria.builder().descricao(TRANSPORTE).build());
    }

    public void descricaoTransporteIgualComContasDiferentes() {
        Categoria categoriaTransporte1 = Categoria.builder().descricao(TRANSPORTE)
            .build();
        
        Set<Conta> contas = new HashSet<>();
        contas.add(ContaDebito.builder().descricao("gasolina").categoria(categoriaTransporte1).build());
        contas.add(ContaDebito.builder().descricao("ipva").categoria(categoriaTransporte1).build());
        categoriaTransporte1.setContas(contas);
        
        Categoria categoriaTransporte2 = Categoria.builder().descricao(TRANSPORTE).build();
        
        assertThat(categoriaTransporte1).isEqualTo(categoriaTransporte2);
    }
    
    public void descricaoDiferente() {
        assertThat(Categoria.builder().descricao(TRANSPORTE).build())
            .isNotEqualTo(Categoria.builder().descricao("habitação").build());        
    }
    
}
