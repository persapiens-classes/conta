package br.edu.ifrn.conta.dominio;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class DonoTests {
    
    private static final String PAPAI = "papai";    

    public void descricaoPapaiIgualSemLancamentos() {
        assertThat(Dono.builder().descricao(PAPAI).build())
            .isEqualTo(Dono.builder().descricao(PAPAI).build());
    }

    public void descricaoTransporteIgualComContasDiferentes() {
        Lancamento lancamentoGasolina = Lancamento.builder().descricao("gasolina").build();
        Lancamento lancamentoIpva = Lancamento.builder().descricao("ipva").build();
        Lancamento lancamentoOnibus = Lancamento.builder().descricao("ônibus").build();
        
        Dono papai1 = Dono.builder().descricao(PAPAI)
            .lancamento(lancamentoGasolina)
            .lancamento(lancamentoIpva)
            .lancamento(lancamentoOnibus)
            .build();
        
        Dono papai2 = Dono.builder().descricao(PAPAI).build();
        
        assertThat(papai1).isEqualTo(papai2);
    }
    
    public void descricaoDiferente() {
        assertThat(Dono.builder().descricao(PAPAI).build())
            .isNotEqualTo(Dono.builder().descricao("mamãe").build());
        
    }
    
}
