package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.Categoria;
import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import javax.inject.Inject;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Example;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = ContaApplication.class)
@WebAppConfiguration
@Test(groups = "contaPatrimonio", dependsOnGroups = "categoria")
public class ContaPatrimonioRepositoryIT extends AbstractTestNGSpringContextTests {
    
    @Inject
    private ContaPatrimonioFabrica contaPatrimonioFabrica;

    @Inject
    private ContaPatrimonioRepository contaPatrimonioRepository;
    
    @BeforeMethod
    void deletarTodos()
    {
        contaPatrimonioRepository.deleteAll();
        assertThat(contaPatrimonioRepository.findAll()).isEmpty();
    }
    
    public void repositorioNaoEhNulo () {
        assertThat(contaPatrimonioRepository).isNotNull();
    }
    
    public void findAllByExample () {
        // cria o ambiente de teste
        contaPatrimonioFabrica.poupanca();

        ContaPatrimonio contaPatrimonioExemplo = ContaPatrimonio.builder()
            .categoria(Categoria.builder().descricao(CategoriaFabrica.BANCO).build())
            .build();
                
        // executa a operacao a ser testada
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(contaPatrimonioRepository.count(Example.of(contaPatrimonioExemplo)))
            .isEqualTo(1);
    }
    
}
