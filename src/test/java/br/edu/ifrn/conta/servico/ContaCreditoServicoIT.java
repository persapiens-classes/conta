package br.edu.ifrn.conta.servico;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.ContaCredito;
import br.edu.ifrn.conta.persistencia.CategoriaFabrica;
import br.edu.ifrn.conta.persistencia.ContaCreditoFabrica;
import javax.inject.Inject;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = ContaApplication.class)
@WebAppConfiguration
@Test(groups = "contaCredito", dependsOnGroups = "categoria")
public class ContaCreditoServicoIT extends AbstractTestNGSpringContextTests {

    @Inject
    private ContaCreditoServico contaCreditoServico;
    
    @Inject
    private ContaCreditoFabrica contaCreditoFabrica;
    
    @Inject
    private CategoriaFabrica categoriaFabrica;
    
    @BeforeMethod
    void deletarTodos()
    {
        contaCreditoServico.deleteAll();
        assertThat(contaCreditoServico.findAll()).isEmpty();
    }
    
    public void repositorioNaoEhNulo () {
        assertThat(contaCreditoServico).isNotNull();
    }
    
    public void salvarUm () {
        // cria o ambiente de teste
        ContaCredito contaCredito = ContaCredito.builder()
            .descricao(ContaCreditoFabrica.ESTAGIO)
            .categoria(categoriaFabrica.salario())
            .build();
        
        // executa a operacao a ser testada
        contaCreditoServico.save(contaCredito);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(contaCreditoServico.findAll().iterator().next()).isEqualTo(contaCredito);
    }
    
    public void deletarUm () {
        // cria o ambiente de teste
        ContaCredito contaCredito = ContaCredito.builder()
            .descricao(ContaCreditoFabrica.ESTAGIO)
            .categoria(categoriaFabrica.salario())
            .build();
        contaCreditoServico.save(contaCredito);
        
        // executa a operacao a ser testada
        contaCreditoServico.delete(contaCredito);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(contaCreditoServico.findAll().iterator().hasNext()).isFalse();
    }
    
}
