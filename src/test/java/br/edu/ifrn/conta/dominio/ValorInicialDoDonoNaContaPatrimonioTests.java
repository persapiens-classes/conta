package br.edu.ifrn.conta.dominio;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;

@Test
public class ValorInicialDoDonoNaContaPatrimonioTests {

    private static final String CARTEIRA = "carteira";
    private static final String PATRIMONIO_INDIVIDUAL = "patrimonio individual";
    private static final String POUPANCA = "poupança";
    
    private static final String PAPAI = "papai";
    private static final String MAMAE = "mamãe";

    private ValorInicialDoDonoNaContaPatrimonio valorInicialDoDonoNaContaPatrimonio(
            String descricaoCategoria, String descricaoDono, BigDecimal valorInicial)
    {
        return ValorInicialDoDonoNaContaPatrimonio.builder().contaPatrimonio(
                ContaPatrimonio.builder().descricao(descricaoCategoria)
                    .categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build()).build())
                .dono(Dono.builder().descricao(descricaoDono).build())
                .valorInicial(valorInicial)
                .build();
    }
    
    public void donoEContaPatrimonioEValorInicialIguais() {        
        assertThat(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, PAPAI, new BigDecimal(100)))
        .isEqualTo(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, PAPAI, new BigDecimal(100)));
    }
    
    public void donoEContaPatrimonioIguaisEValorInicialDiferente() {
        assertThat(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, PAPAI, new BigDecimal(100)))
        .isNotEqualTo(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, PAPAI, new BigDecimal(999)));
    }
    
    public void donoEValorInicialIguaisEContaPatrimonioDiferente() {
        assertThat(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, PAPAI, new BigDecimal(100)))
        .isNotEqualTo(valorInicialDoDonoNaContaPatrimonio(POUPANCA, PAPAI, new BigDecimal(100)));
    }
    
    public void contaPatrimonioEValorInicialIguaisEDonoDiferente() {
        assertThat(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, PAPAI, new BigDecimal(100)))
        .isNotEqualTo(valorInicialDoDonoNaContaPatrimonio(CARTEIRA, MAMAE, new BigDecimal(100)));
    }
    
    private ValorInicialDoDonoNaContaPatrimonio valorInicialDoDonoNaContaPatrimonio(
        BigDecimal valorInicial, String descricaoDono, String descricaoConta)
    {
        return ValorInicialDoDonoNaContaPatrimonio.builder()
            .valorInicial(valorInicial)
            .dono(Dono.builder().descricao(descricaoDono).build())
            .contaPatrimonio(ContaPatrimonio.builder()
                .descricao(descricaoConta)
                .categoria(Categoria.builder().descricao(PATRIMONIO_INDIVIDUAL).build()).build())
            .build();
    }
    
    public void compareToComDonosDiferentes() {
        Set<ValorInicialDoDonoNaContaPatrimonio> valoresIniciais = new TreeSet<>();

        ValorInicialDoDonoNaContaPatrimonio valorInicialCarteiraPapai = valorInicialDoDonoNaContaPatrimonio(
            new BigDecimal(100), PAPAI, CARTEIRA);
        valoresIniciais.add(valorInicialCarteiraPapai);

        ValorInicialDoDonoNaContaPatrimonio valorInicialCarteiraMamae = valorInicialDoDonoNaContaPatrimonio(
            new BigDecimal(100), MAMAE, CARTEIRA);
        valoresIniciais.add(valorInicialCarteiraMamae);
        
        assertThat(valoresIniciais.iterator().next()).isEqualTo(valorInicialCarteiraMamae);
    }
    
    public void compareToComValoresIniciaisDiferentes() {
        Set<ValorInicialDoDonoNaContaPatrimonio> valoresIniciais = new TreeSet<>();

        ValorInicialDoDonoNaContaPatrimonio valorInicial999 = valorInicialDoDonoNaContaPatrimonio(
            new BigDecimal(999), PAPAI, CARTEIRA);
        valoresIniciais.add(valorInicial999);

        ValorInicialDoDonoNaContaPatrimonio valorInicial100 = valorInicialDoDonoNaContaPatrimonio(
            new BigDecimal(100), PAPAI, CARTEIRA);
        valoresIniciais.add(valorInicial100);
        
        assertThat(valoresIniciais.iterator().next()).isEqualTo(valorInicial100);
    }
    
    public void compareToComContaPatrimonioDiferentes() {
        Set<ValorInicialDoDonoNaContaPatrimonio> valoresIniciais = new TreeSet<>();

        ValorInicialDoDonoNaContaPatrimonio valorInicialPoupanca = valorInicialDoDonoNaContaPatrimonio(
            new BigDecimal(100), PAPAI, POUPANCA);
        valoresIniciais.add(valorInicialPoupanca);

        ValorInicialDoDonoNaContaPatrimonio valorInicialCarteira = valorInicialDoDonoNaContaPatrimonio(
            new BigDecimal(100), PAPAI, CARTEIRA);
        valoresIniciais.add(valorInicialCarteira);
        
        assertThat(valoresIniciais.iterator().next()).isEqualTo(valorInicialCarteira);
    }
    
}
