package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.domain.ContaCredito;
import br.edu.ifrn.conta.persistence.CategoriaFactory;
import br.edu.ifrn.conta.persistence.ContaCreditoFactory;

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
public class ContaCreditoRestClientIT {

    private final String protocol = "http";
    private final String servername = "localhost";

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
    
    private CategoriaRestClientFactory categoriaRestClientFactory() {
        return CategoriaRestClientFactory.builder()
                .protocol(protocol)
                .servername(servername)
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .build();
    }

    private ContaCreditoRestClient contaCreditoRestClient() {
        return ContaCreditoRestClientFactory.builder()
                .protocol(protocol)
                .servername(servername)
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .categoriaRestClientFactory(categoriaRestClientFactory())
                .build()
                .contaCreditoRestClient();
    }

    @Test
    public void salvarUm() {        
        // executa a operacao a ser testada
        String descricao = ContaCreditoFactory.ESTAGIO;
        String categoriaDescricao = CategoriaFactory.SALARIO;
        
        ContaCredito estagio = ContaCredito.builder()
                .descricao(descricao)
                .categoria(this.categoriaRestClientFactory().categoria(categoriaDescricao))
                .build();

        // verifica a operacao save
        assertThat(contaCreditoRestClient().save(estagio))
        	.isNotNull();

        // verifica a operacao findByDescricao
        assertThat(contaCreditoRestClient().findByDescricao(descricao).getDescricao())
                .isEqualTo(estagio.getDescricao());
        
        // verifica a operacao findAll
        assertThat(contaCreditoRestClient().findAll())
                .isNotEmpty();        
    }

}
