package br.edu.ifrn.conta.servico;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.Dono;
import javax.inject.Inject;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = ContaApplication.class)
@WebAppConfiguration
@Test
public class DonoServicoIT extends AbstractTestNGSpringContextTests {

    @Inject
    private DonoServico donoServico;
    
    public void repositorioNaoEhNulo () {
        assertThat(donoServico).isNotNull();
    }
    
    public void salvarUm () {
        // cria o ambiente de teste
        Dono dono = Dono.builder().descricao("Papai").build();
        
        // executa a operacao a ser testada
        donoServico.save(dono);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(donoServico.iterator().next()).isEqualTo(dono);
    }
    
    public void deletarUm () {
        // cria o ambiente de teste
        Dono dono = Dono.builder().descricao("Papai").build();
        donoServico.save(dono);
        
        // executa a operacao a ser testada
        donoServico.delete(dono);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(donoServico.iterator().hasNext()).isFalse();
    }
    
}
