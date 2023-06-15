package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.controller.DonoDTO;
import br.edu.ifrn.conta.persistence.DonoFactory;

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
public class DonoRestClientIT {

    private final String protocol = "http";
    private final String servername = "localhost";

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private DonoRestClient donoRestClient() {
        return DonoRestClientFactory.builder()
                .protocol(protocol)
                .servername(servername)
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .build().donoRestClient();
    }
    
    @Test
    public void deletarUm() {
        // cria o ambiente de teste
        String descricao = "DONO EXCEPCIONAL";
        donoRestClient().save(DonoDTO.builder().descricao(descricao).build());
        assertThat(donoRestClient().findByDescricao(descricao).getDescricao())
        	.isEqualTo(descricao);
        
        // executa a operacao a ser testada
        donoRestClient().deleteByDescricao(descricao);
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(donoRestClient().findByDescricao(descricao))
        	.isNull();
    }

    @Test
    public void salvarUm() {        
        // executa a operacao a ser testada
        String descricao = DonoFactory.PAPAI;
        
        DonoDTO dono = DonoDTO.builder().descricao(descricao).build();

        // verifica a operacao save
        assertThat(donoRestClient().save(dono))
        	.isNotNull();

        // verifica a operacao findByDescricao
        assertThat(donoRestClient().findByDescricao(descricao).getDescricao())
                .isEqualTo(dono.getDescricao());
        
        // verifica a operacao findAll
        assertThat(donoRestClient().findAll())
                .isNotEmpty();        
    }

}
