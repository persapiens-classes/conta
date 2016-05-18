package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.Dono;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = ContaApplication.class)
@WebAppConfiguration
@Test
public class DonoRepositorioMemoriaIT extends AbstractTestNGSpringContextTests {

    @Inject
    private DonoRepositorio donoRepositorio;
    
    public void repositorioNaoEhNulo () {
        assertThat(donoRepositorio).isNotNull();
    }
    
    public void salvarUm () {
        // cria o ambiente de teste
        Dono dono = Dono.builder().descricao("Papai").build();
        
        // executa a operacao a ser testada
        donoRepositorio.save(dono);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(donoRepositorio.iterator().next()).isEqualTo(dono);
    }
    
    public void deletarUm () {
        // cria o ambiente de teste
        Dono dono = Dono.builder().descricao("Papai").build();
        donoRepositorio.save(dono);
        
        // executa a operacao a ser testada
        donoRepositorio.delete(dono);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(donoRepositorio.iterator().hasNext()).isFalse();
    }
    
}
