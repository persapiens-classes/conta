package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.domain.Categoria;
import br.edu.ifrn.conta.persistence.CategoriaFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoriaRestClientIT {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private CategoriaRestClient categoriaRestClient() {
        return CategoriaRestClient.builder()
                .entityRestHelper(RestClientHelper.<Categoria>builder()
                    .endpoint("categorias")
                    .protocol("http")
                    .servername("localhost")
                    .port(port)
                    .restTemplate(testRestTemplate.getRestTemplate())
                    .build())
                .build();
    }

    @Test
    public void salvarUm() {        
        // executa a operacao a ser testada
        String descricao = CategoriaFactory.SALARIO;
        
        Categoria categoria = Categoria.builder().descricao(descricao).build();

        // verifica a operacao save
        assertThat(categoriaRestClient().save(categoria))
        	.isNotNull();

        // verifica a operacao findByDescricao
        assertThat(categoriaRestClient().findByDescricao(descricao).getDescricao())
                .isEqualTo(categoria.getDescricao());
        
        // verifica a operacao findAll
        assertThat(categoriaRestClient().findAll())
                .isNotEmpty();        
    }

}
