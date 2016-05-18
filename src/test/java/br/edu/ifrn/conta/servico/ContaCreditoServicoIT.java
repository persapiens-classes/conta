package br.edu.ifrn.conta.servico;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.Categoria;
import br.edu.ifrn.conta.dominio.ContaCredito;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = ContaApplication.class)
@WebAppConfiguration
@Test
public class ContaCreditoServicoIT extends AbstractTestNGSpringContextTests {

    @Inject
    private ContaCreditoServico contaCreditoServico;
    
    public void repositorioNaoEhNulo () {
        assertThat(contaCreditoServico).isNotNull();
    }
    
    public void salvarUm () {
        // cria o ambiente de teste
        ContaCredito contaCredito = ContaCredito.builder()
            .descricao("gasolina")
            .categoria(Categoria.builder().descricao("transporte").build())
            .build();
        
        // executa a operacao a ser testada
        contaCreditoServico.save(contaCredito);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(contaCreditoServico.iterator().next()).isEqualTo(contaCredito);
    }
    
    public void deletarUm () {
        // cria o ambiente de teste
        ContaCredito contaCredito = ContaCredito.builder()
            .descricao("gasolina")
            .categoria(Categoria.builder().descricao("transporte").build())
            .build();
        contaCreditoServico.save(contaCredito);
        
        // executa a operacao a ser testada
        contaCreditoServico.delete(contaCredito);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(contaCreditoServico.iterator().hasNext()).isFalse();
    }
    
}
