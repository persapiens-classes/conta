package br.edu.ifrn.conta.servico;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.ContaCredito;
import br.edu.ifrn.conta.dominio.ContaDebito;
import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import br.edu.ifrn.conta.dominio.Dono;
import br.edu.ifrn.conta.dominio.Lancamento;
import br.edu.ifrn.conta.persistencia.ContaCreditoFabrica;
import br.edu.ifrn.conta.persistencia.ContaDebitoFabrica;
import br.edu.ifrn.conta.persistencia.ContaPatrimonioFabrica;
import br.edu.ifrn.conta.persistencia.DonoFabrica;
import br.edu.ifrn.conta.persistencia.LancamentoFabrica;
import br.edu.ifrn.conta.persistencia.ValorInicialDoDonoNaContaPatrimonioFabrica;
import java.math.BigDecimal;
import java.util.Date;
import javax.inject.Inject;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = ContaApplication.class)
@WebAppConfiguration
@Test(groups = "lancamento", dependsOnGroups = {"valorInicialDoDonoNaContaPatrimonio", "contaCredito", "contaDebito"})
public class LancamentoServicoIT extends AbstractTestNGSpringContextTests {
    
    @Inject
    private LancamentoFabrica lancamentoFabrica;
    
    @Inject
    private DonoFabrica donoFabrica;
    
    @Inject
    private LancamentoServico lancamentoServico;
    
    @Inject
    private ValorInicialDoDonoNaContaPatrimonioFabrica valorInicialDoDonoNaContaPatrimonioFabrica;
    
    @Inject
    private ContaPatrimonioFabrica contaPatrimonioFabrica;
    
    @Inject
    private ContaCreditoFabrica contaCreditoFabrica;
    
    @Inject
    private ContaDebitoFabrica contaDebitoFabrica;
    
    @BeforeMethod
    void deletarTodos()
    {
        lancamentoServico.deleteAll();
        assertThat(lancamentoServico.findAll()).isEmpty();
    }
    
    public void repositorioNaoEhNulo () {
        assertThat(lancamentoServico).isNotNull();
    }
    
    public void saldo800() {
        // cria o ambiente de teste
        Dono papai = donoFabrica.papai();

        ContaPatrimonio poupanca = 
            contaPatrimonioFabrica.poupanca();

        ContaDebito gasolina = 
            contaDebitoFabrica.gasolina();

        ContaCredito bolsa = 
            contaCreditoFabrica.estagio();
        
        valorInicialDoDonoNaContaPatrimonioFabrica.valorInicialDoDonoNaContaPatrimonio(
            papai, poupanca, new BigDecimal(1000));
        
        lancamentoFabrica.lancamento(papai, gasolina, poupanca, new BigDecimal(500));
        lancamentoFabrica.lancamento(papai, poupanca, bolsa, new BigDecimal(300));
        
        // executa a operacao a ser testada        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(lancamentoServico.saldo(papai, poupanca))
            .isEqualTo(new BigDecimal(800).setScale(2));
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void lancamentoComContaEntradaInvalida() {
        Lancamento lancamento = Lancamento.builder()
            .contaEntrada(contaCreditoFabrica.estagio())
            .contaSaida(contaPatrimonioFabrica.poupanca())
            .valor(BigDecimal.TEN)
            .data(new Date())
            .dono(donoFabrica.papai())
            .build();
        
        lancamentoServico.save(lancamento);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void lancamentoComContaSaidaInvalida() {
        Lancamento lancamento = Lancamento.builder()
            .contaEntrada(contaPatrimonioFabrica.poupanca())
            .contaSaida(contaDebitoFabrica.gasolina())
            .valor(BigDecimal.TEN)
            .data(new Date())
            .dono(donoFabrica.papai())
            .build();
        
        lancamentoServico.save(lancamento);
    }
    
    public void transferenciaDePapaiParaMamae() {
        lancamentoServico.transferir(BigDecimal.TEN
            , donoFabrica.papai(), contaDebitoFabrica.despesaComConjuge(), contaPatrimonioFabrica.poupanca()
            , donoFabrica.mamae(), contaPatrimonioFabrica.poupanca(), contaCreditoFabrica.receitaComConjuge());
    }
}
