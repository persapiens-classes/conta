package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.Categoria;
import br.edu.ifrn.conta.dominio.ContaDebito;
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
@Test(groups = "contaDebito", dependsOnGroups = "categoria")
public class ContaDebitoRepositoryIT extends AbstractTestNGSpringContextTests {
    
    @Inject
    private ContaDebitoFabrica contaDebitoFabrica;

    @Inject
    private ContaDebitoRepository contaDebitoRepository;
    
    @BeforeMethod
    void deletarTodos()
    {
        contaDebitoRepository.deleteAll();
        assertThat(contaDebitoRepository.findAll()).isEmpty();
    }
    
    public void repositorioNaoEhNulo () {
        assertThat(contaDebitoRepository).isNotNull();
    }
    
    public void findAllByExample () {
        // cria o ambiente de teste
        ContaDebito contaDebito = contaDebitoFabrica.gasolina();

        ContaDebito contaDebitoExemplo = ContaDebito.builder()
            .categoria(Categoria.builder().descricao(CategoriaFabrica.TRANSPORTE).build())
            .build();
                
        // executa a operacao a ser testada
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(contaDebitoRepository.findAll(Example.of(contaDebitoExemplo)).iterator().next())
            .isEqualTo(contaDebito);
    }
    
}
