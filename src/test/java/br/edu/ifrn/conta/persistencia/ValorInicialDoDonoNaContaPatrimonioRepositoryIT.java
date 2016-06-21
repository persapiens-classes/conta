package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import br.edu.ifrn.conta.dominio.Dono;
import br.edu.ifrn.conta.dominio.ValorInicialDoDonoNaContaPatrimonio;
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
@Test(groups = "valorInicialDoDonoNaContaPatrimonio", dependsOnGroups = "contaPatrimonio")
public class ValorInicialDoDonoNaContaPatrimonioRepositoryIT extends AbstractTestNGSpringContextTests {
    
    @Inject
    private ValorInicialDoDonoNaContaPatrimonioRepository valorInicialDoDonoNaContaPatrimonioRepository;
    
    @Inject
    private DonoFabrica donoFabrica;
    
    @Inject
    private ValorInicialDoDonoNaContaPatrimonioFabrica valorInicialDoDonoNaContaPatrimonioFabrica;
    
    @Inject
    private ContaPatrimonioFabrica contaPatrimonioFabrica;
    
    @BeforeMethod
    void deletarTodos()
    {
        valorInicialDoDonoNaContaPatrimonioRepository.deleteAll();
        assertThat(valorInicialDoDonoNaContaPatrimonioRepository.findAll()).isEmpty();
    }
    
    public void repositorioNaoEhNulo () {
        assertThat(valorInicialDoDonoNaContaPatrimonioRepository).isNotNull();
    }
    
    public void findByDonoAndContaPatrimonio() {
        // cria o ambiente de teste        
        Dono papai = donoFabrica.papai();
                
        ContaPatrimonio contaPatrimonio = contaPatrimonioFabrica.poupanca();
        
        ValorInicialDoDonoNaContaPatrimonio valorInicialDoDonoNaContaPatrimonio
            = valorInicialDoDonoNaContaPatrimonioFabrica.valorInicialDoDonoNaContaPatrimonio(papai, contaPatrimonio, new BigDecimal(100));
        
        // executa a operacao a ser testada        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(valorInicialDoDonoNaContaPatrimonioRepository.findByDonoAndContaPatrimonio(papai, contaPatrimonio))
            .isEqualTo(valorInicialDoDonoNaContaPatrimonio);
    }
}
