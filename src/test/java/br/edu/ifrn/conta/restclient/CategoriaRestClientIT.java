package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.controller.CategoriaDTO;
import br.edu.ifrn.conta.persistence.CategoriaFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoriaRestClientIT {

    private final String protocol = "http";
    private final String servername = "localhost";

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private CategoriaRestClient categoriaRestClient() {
        return CategoriaRestClientFactory.builder()
                .protocol(protocol)
                .servername(servername)
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .build().categoriaRestClient();
    }

    @Test
    public void salvarUm() {        
        // executa a operacao a ser testada
        String descricao = CategoriaFactory.SALARIO;
        
        CategoriaDTO categoria = CategoriaDTO.builder().descricao(descricao).build();

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
