package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.Categoria;
import javax.inject.Inject;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = ContaApplication.class)
@WebAppConfiguration
@Test
public class CategoriaRepositorioMemoriaIT extends AbstractTestNGSpringContextTests {

    @Inject
    private CategoriaRepositorio categoriaRepositorio;
    
    public void repositorioNaoEhNulo () {
        assertThat(categoriaRepositorio).isNotNull();
    }
    
    public void salvarUm () {
        // cria o ambiente de teste
        Categoria categoria = Categoria.builder().descricao("Transporte").build();
        
        // executa a operacao a ser testada
        categoriaRepositorio.save(categoria);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(categoriaRepositorio.iterator().next()).isEqualTo(categoria);
    }
    
    public void deletarUm () {
        // cria o ambiente de teste
        Categoria categoria = Categoria.builder().descricao("Transporte").build();
        categoriaRepositorio.save(categoria);
        
        // executa a operacao a ser testada
        categoriaRepositorio.delete(categoria);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(categoriaRepositorio.iterator().hasNext()).isFalse();
    }
    
}
