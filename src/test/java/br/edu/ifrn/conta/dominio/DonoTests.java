package br.edu.ifrn.conta.dominio;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class DonoTests {

    public void descricaoPapaiIgualSemLancamentos() {
        assertThat(Dono.builder().descricao("papai").build())
            .isEqualTo(Dono.builder().descricao("papai").build());
    }

    public void descricaoTransporteIgualComContasDiferentes() {
        Lancamento lancamentoGasolina = Lancamento.builder().descricao("gasolina").build();
        Lancamento lancamentoIpva = Lancamento.builder().descricao("ipva").build();
        Lancamento lancamentoOnibus = Lancamento.builder().descricao("ônibus").build();
        
        Dono papai1 = Dono.builder().descricao("papai")
            .lancamento(lancamentoGasolina)
            .lancamento(lancamentoIpva)
            .lancamento(lancamentoOnibus)
            .build();
        
        Dono papai2 = Dono.builder().descricao("papai").build();
        
        assertThat(papai1).isEqualTo(papai2);
    }
    
    public void descricaoDiferente() {
        assertThat(Dono.builder().descricao("papai").build())
            .isNotEqualTo(Dono.builder().descricao("mamãe").build());
        
    }
    
}
