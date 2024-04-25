package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dto.ContaCreditoDTO;
import static br.edu.ifrn.conta.util.CategoriaConstants.SALARIO;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
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
        String descricao = "Emprego Fixo";
        String categoriaDescricao = SALARIO;
        
        ContaCreditoDTO estagio = ContaCreditoDTO.builder()
            .descricao(descricao)
            .categoria(this.categoriaRestClientFactory().categoria(categoriaDescricao))
            .build();

        // verifica a operacao save
        assertThat(contaCreditoRestClient().save(estagio))
        	.isNotNull();

        // verifica a operacao findByDescricao
        assertThat(contaCreditoRestClient().findByDescricao(descricao).get().getDescricao())
                .isEqualTo(estagio.getDescricao());
        
        // verifica a operacao findAll
        assertThat(contaCreditoRestClient().findAll())
                .isNotEmpty();        
    }

}
