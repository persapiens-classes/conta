package br.edu.ifrn.conta.dominio;

import java.math.BigDecimal;
import java.util.Date;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class LancamentoTests {

    private Lancamento lancamento(Date data, BigDecimal valor, String descricao)
    {        
        return Lancamento.builder()
            .descricao(descricao)
            .dono(Dono.builder().descricao("papai").build())
            .valor(valor)
            .data(data)
            .contaEntrada(ContaDebito.builder()
                .descricao("gasolina").categoria(
                    Categoria.builder().descricao("transporte").build()).build())
            .contaSaida(ContaPatrimonio.builder()
                .valorInicial(new BigDecimal(100))
                .descricao("carteira").categoria(
                    Categoria.builder().descricao("patrimônio pessoal").build()).build())
            .build();        
    }
    
    public void donoValorDataContaEntradaContaSaidaIguaisComDescricaoDiferente() {
        Date hoje = new Date();
        
        Lancamento lancamentoGasolina1 = lancamento(hoje, new BigDecimal(100), "comprar gasolina no Posto Predileto");        
        Lancamento lancamentoGasolina2 = lancamento(hoje, new BigDecimal(100), "outra descrição qualquer");
        
        assertThat(lancamentoGasolina1).isEqualTo(lancamentoGasolina2);
    }

    public void donoValorDataContaEntradaContaSaidaDescricaoIguaisComValorDiferente() {
        Date hoje = new Date();
        
        Lancamento lancamentoGasolina1 = lancamento(hoje, new BigDecimal(200), "comprar gasolina no Posto Predileto");
        Lancamento lancamentoGasolina2 = lancamento(hoje, new BigDecimal(100), "comprar gasolina no Posto Predileto");
        
        assertThat(lancamentoGasolina1).isNotEqualTo(lancamentoGasolina2);
    }
    
}
