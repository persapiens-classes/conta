package br.edu.ifrn.conta.servico;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.Dono;
import br.edu.ifrn.conta.persistencia.DonoFabrica;
import javax.inject.Inject;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = ContaApplication.class)
@WebAppConfiguration
@Test(groups = "dono")
public class DonoServicoIT extends AbstractTestNGSpringContextTests {

    @Inject
    private DonoServico donoServico;
    
    public void repositorioNaoEhNulo () {
        assertThat(donoServico).isNotNull();
    }
    
    @BeforeMethod
    void deletarTodos()
    {
        donoServico.deleteAll();
        assertThat(donoServico.findAll()).isEmpty();
    }
    
    public void salvarUm () {
        // cria o ambiente de teste
        Dono dono = Dono.builder().descricao(DonoFabrica.PAPAI).build();
        
        // executa a operacao a ser testada
        donoServico.save(dono);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(donoServico.findAll().iterator().next()).isEqualTo(dono);
    }
    
    public void deletarUm () {
        // cria o ambiente de teste
        Dono dono = Dono.builder().descricao(DonoFabrica.PAPAI).build();
        donoServico.save(dono);
        
        // executa a operacao a ser testada
        donoServico.delete(dono);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(donoServico.findAll().iterator().hasNext()).isFalse();
    }
    
}
