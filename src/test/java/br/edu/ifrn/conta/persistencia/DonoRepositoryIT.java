package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.Dono;
import static br.edu.ifrn.conta.persistencia.DonoFabrica.MAMAE;
import static br.edu.ifrn.conta.persistencia.DonoFabrica.PAPAI;
import javax.inject.Inject;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = ContaApplication.class)
@WebAppConfiguration
@Test(groups = "dono")
public class DonoRepositoryIT extends AbstractTestNGSpringContextTests {
    
    @Inject
    private DonoRepository donoRepository;
    
    @Inject
    private DonoFabrica donoFabrica;
    
    @BeforeMethod
    void deletarTodos()
    {
        donoRepository.deleteAll();
        assertThat(donoRepository.findAll()).isEmpty();
    }
    
    public void repositorioNaoEhNulo () {
        assertThat(donoRepository).isNotNull();
    }
    
    public void findByDescricao() {
        // cria o ambiente de teste
        Dono papai = donoFabrica.papai();
        Dono mamae = donoFabrica.mamae();
        
        // executa a operacao a ser testada        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(donoRepository.findByDescricao(PAPAI)).isEqualTo(papai);
        assertThat(donoRepository.findByDescricao(MAMAE)).isEqualTo(mamae);
    }
    
    public void countByDescricao() {
        // cria o ambiente de teste
        donoFabrica.papai();
        donoFabrica.mamae();
        
        // executa a operacao a ser testada        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(donoRepository.countByDescricaoContains("a")).isEqualTo(2);
    }
    
    public void deleteByDescricao () {
        // cria o ambiente de teste
        donoFabrica.papai();
        
        // executa a operacao a ser testada
        donoRepository.deleteByDescricao(PAPAI);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(donoRepository.findAll()).isEmpty();
    }
}
