package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.ContaCredito;
import br.edu.ifrn.conta.dominio.ContaDebito;
import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import br.edu.ifrn.conta.dominio.Dono;
import java.math.BigDecimal;
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
public class LancamentoRepositoryIT extends AbstractTestNGSpringContextTests {
    
    @Inject
    private FabricaDominio dominioFactory;
    
    @Inject
    private LancamentoRepository lancamentoRepository;
    
    @BeforeMethod
    void deletarTodos()
    {
        lancamentoRepository.deleteAll();
        assertThat(lancamentoRepository.findAll()).isEmpty();
    }
    
    public void repositorioNaoEhNulo () {
        assertThat(lancamentoRepository).isNotNull();
    }
    
    public void saldo800() {
        // cria o ambiente de teste
        Dono papai = dominioFactory.papai();

        ContaPatrimonio poupanca = 
            dominioFactory.poupanca();

        ContaDebito gasolina = 
            dominioFactory.gasolina();

        ContaCredito estagio = 
            dominioFactory.estagio();
        
        dominioFactory.valorInicialDoDonoNaContaPatrimonio(
            papai, poupanca, new BigDecimal(1000));
        
        dominioFactory.lancamento(papai, gasolina, poupanca, new BigDecimal(500));
        dominioFactory.lancamento(papai, poupanca, estagio, new BigDecimal(300));
        
        // executa a operacao a ser testada        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(lancamentoRepository.saldo(papai, poupanca))
            .isEqualTo(new BigDecimal(800).setScale(2));
    }
}
