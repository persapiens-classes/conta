package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.controller.ContaPatrimonioDTO;
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
public class ContaPatrimonioRestClientIT {

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

    private ContaPatrimonioRestClient contaPatrimonioRestClient() {
        return ContaPatrimonioRestClientFactory.builder()
                .protocol(protocol)
                .servername(servername)
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .categoriaRestClientFactory(categoriaRestClientFactory())
                .build()
                .contaPatrimonioRestClient();
    }

    @Test
    public void salvarUm() {        
        // executa a operacao a ser testada
        String descricao = "Conta Super";
        String categoriaDescricao = CategoriaFactory.BANCO;
        
        ContaPatrimonioDTO poupanca = ContaPatrimonioDTO.builder()
                .descricao(descricao)
                .categoria(this.categoriaRestClientFactory().categoria(categoriaDescricao))
                .build();

        // verifica a operacao save
        assertThat(contaPatrimonioRestClient().save(poupanca))
        	.isNotNull();

        // verifica a operacao findByDescricao
        assertThat(contaPatrimonioRestClient().findByDescricao(descricao).getDescricao())
                .isEqualTo(poupanca.getDescricao());
        
        // verifica a operacao findAll
        assertThat(contaPatrimonioRestClient().findAll())
                .isNotEmpty();        
    }

}
