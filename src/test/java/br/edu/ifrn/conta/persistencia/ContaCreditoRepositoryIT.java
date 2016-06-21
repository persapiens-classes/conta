package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.Categoria;
import br.edu.ifrn.conta.dominio.ContaCredito;
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
@Test(groups = "contaCredito", dependsOnGroups = "categoria")
public class ContaCreditoRepositoryIT extends AbstractTestNGSpringContextTests {

    @Inject
    private ContaCreditoRepository contaCreditoRepository;
    
    @Inject
    private ContaCreditoFabrica contaCreditoFabrica;
    
    @BeforeMethod
    void deletarTodos()
    {
        contaCreditoRepository.deleteAll();
        assertThat(contaCreditoRepository.findAll()).isEmpty();
    }
    
    public void repositorioNaoEhNulo () {
        assertThat(contaCreditoRepository).isNotNull();
    }
    
    public void findOneByExample () {
        // cria o ambiente de teste
        ContaCredito contaCredito = contaCreditoFabrica.estagio();

        ContaCredito contaCreditoExemplo = ContaCredito.builder()
            .categoria(Categoria.builder().descricao(CategoriaFabrica.SALARIO).build())
            .build();
                       
        // executa a operacao a ser testada
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(contaCreditoRepository.findOne(Example.of(contaCreditoExemplo)))
            .isEqualTo(contaCredito);
    }
    
}
