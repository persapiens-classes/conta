package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dto.CategoriaDTO;

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
        String descricao = "Receita Isenta";
        
        CategoriaDTO categoria = CategoriaDTO.builder().descricao(descricao).build();

        // verifica a operacao save
        assertThat(categoriaRestClient().save(categoria))
        	.isNotNull();

        // verifica a operacao findByDescricao
        assertThat(categoriaRestClient().findByDescricao(descricao).get().getDescricao())
                .isEqualTo(categoria.getDescricao());
        
        // verifica a operacao findAll
        assertThat(categoriaRestClient().findAll())
                .isNotEmpty();        
    }

}
