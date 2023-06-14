package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.domain.Dono;
import br.edu.ifrn.conta.persistence.DonoFactory;
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
public class DonoRestClientIT {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private DonoRestClient donoApi() {
        return DonoRestClient.builder()
                .protocol("http")
                .servername("localhost")
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .build();
    }
    
    @Test
    public void deletarUm() {
        // cria o ambiente de teste
        String descricao = "DONO EXCEPCIONAL";
        donoApi().save(Dono.builder().descricao(descricao).build());
        assertThat(donoApi().findByDescricao(descricao).getDescricao())
        	.isEqualTo(descricao);
        
        // executa a operacao a ser testada
        donoApi().deleteByDescricao(descricao);
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(donoApi().findByDescricao(descricao))
        	.isNull();
    }

    @Test
    public void salvarUm() {        
        // executa a operacao a ser testada
        String descricao = DonoFactory.PAPAI;
        
        Dono dono = Dono.builder().descricao(descricao).build();

        // verifica a operacao save
        assertThat(donoApi().save(dono))
        	.isNotNull();

        // verifica a operacao findByDescricao
        assertThat(donoApi().findByDescricao(descricao).getDescricao())
                .isEqualTo(dono.getDescricao());
        
        // verifica a operacao findAll
        assertThat(donoApi().findAll())
                .isNotEmpty();        
    }

}
