package br.edu.ifrn.conta.persistencia;

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
public class ContaCreditoRepositorioMemoriaIT extends AbstractTestNGSpringContextTests {

    @Inject
    private ContaCreditoRepositorio contaCreditoRepositorio;
    
    public void repositorioNaoEhNulo () {
        assertThat(contaCreditoRepositorio).isNotNull();
    }
    
    public void salvarUm () {
        // cria o ambiente de teste
        ContaCredito contaCredito = ContaCredito.builder()
            .descricao("gasolina")
            .categoria(Categoria.builder().descricao("transporte").build())
            .build();
        
        // executa a operacao a ser testada
        contaCreditoRepositorio.save(contaCredito);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(contaCreditoRepositorio.iterator().next()).isEqualTo(contaCredito);
    }
    
    public void deletarUm () {
        // cria o ambiente de teste
        ContaCredito contaCredito = ContaCredito.builder()
            .descricao("gasolina")
            .categoria(Categoria.builder().descricao("transporte").build())
            .build();
        contaCreditoRepositorio.save(contaCredito);
        
        // executa a operacao a ser testada
        contaCreditoRepositorio.delete(contaCredito);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(contaCreditoRepositorio.iterator().hasNext()).isFalse();
    }
    
}
