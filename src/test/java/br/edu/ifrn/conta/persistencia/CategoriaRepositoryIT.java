package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.Categoria;
import javax.inject.Inject;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = ContaApplication.class)
@WebAppConfiguration
@Test(groups = "categoria")
public class CategoriaRepositoryIT extends AbstractTestNGSpringContextTests {

    @Inject
    private CategoriaRepository categoriaRepository;
    
    @Inject
    private CategoriaFabrica categoriaFabrica;
    
    @BeforeMethod
    void deletarTodos()
    {
        categoriaRepository.deleteAll();
        assertThat(categoriaRepository.findAll()).isEmpty();
    }
    
    public void repositorioNaoEhNulo () {
        assertThat(categoriaRepository).isNotNull();
    }
    
    public void deletarUm () {
        // cria o ambiente de teste
        Categoria categoria = categoriaFabrica.transporte();
        
        // executa a operacao a ser testada
        categoriaRepository.delete(categoria);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(categoriaRepository.findOne(categoria.getId())).isNull();
    }
    
    public void salvarUm () {
        // cria o ambiente de teste
        Categoria categoria = Categoria.builder().descricao(CategoriaFabrica.TRANSPORTE).build();
        
        // executa a operacao a ser testada
        categoriaRepository.save(categoria);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(categoriaRepository.findAll().iterator().next()).isEqualTo(categoria);
    }
    
}
