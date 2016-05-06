package br.edu.ifrn.conta.dominio;

import java.math.BigDecimal;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class DonoTests {
    
    private static final String PAPAI = "papai";    
    private static final String MAMAE = "mamãe";

    public void descricaoPapaiIgualSemLancamentos() {
        assertThat(Dono.builder().descricao(PAPAI).build())
            .isEqualTo(Dono.builder().descricao(PAPAI).build());
    }

    public void descricaoTransporteIgualComLancamentosDiferentes() {
        Lancamento lancamentoGasolina = Lancamento.builder().descricao("gasolina").build();
        Lancamento lancamentoIpva = Lancamento.builder().descricao("ipva").build();
        
        ValorInicialDoDonoNaContaPatrimonio valorInicialNaCarteira = ValorInicialDoDonoNaContaPatrimonio.builder()
            .contaPatrimonio(ContaPatrimonio.builder().descricao("carteira")
                .categoria(Categoria.builder().descricao("patrimônio individual").build()).build())
            .valorInicial(new BigDecimal(100))
            .build();
        
        ValorInicialDoDonoNaContaPatrimonio valorInicialNoBanco = ValorInicialDoDonoNaContaPatrimonio.builder()
            .contaPatrimonio(ContaPatrimonio.builder().descricao("banco")
                .categoria(Categoria.builder().descricao("patrimônio individual").build()).build())
            .valorInicial(new BigDecimal(1000))
            .build();
    
        Dono papai1 = Dono.builder().descricao(PAPAI)
            .lancamento(lancamentoGasolina)
            .lancamento(lancamentoIpva)
            .valorInicialNaContaPatrimonio(valorInicialNaCarteira)
            .valorInicialNaContaPatrimonio(valorInicialNoBanco)
            .build();
        
        Dono papai2 = Dono.builder().descricao(PAPAI).build();
        
        assertThat(papai1).isEqualTo(papai2);
    }
    
    public void descricaoDiferente() {
        assertThat(Dono.builder().descricao(PAPAI).build())
            .isNotEqualTo(Dono.builder().descricao(MAMAE).build());
        
    }
    
}
