package br.edu.ifrn.conta.restclient;

import java.math.BigDecimal;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dto.ValorInicialDoDonoNaContaPatrimonioDTO;
import static br.edu.ifrn.conta.util.CategoriaConstants.BANCO;
import static br.edu.ifrn.conta.util.ContaPatrimonioConstants.POUPANCA;
import static br.edu.ifrn.conta.util.DonoConstants.MAMAE;

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
public class ValorInicialDoDonoNaContaPatrimonioRestClientIT {

    private final String protocol = "http";
    private final String servername = "localhost";

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private DonoRestClientFactory donoRestClientFactory() {
        return DonoRestClientFactory.builder()
                .protocol(protocol)
                .servername(servername)
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .build();
    }

    private CategoriaRestClientFactory categoriaRestClientFactory() {
        return CategoriaRestClientFactory.builder()
                .protocol(protocol)
                .servername(servername)
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .build();
    }

    private ContaPatrimonioRestClientFactory contaPatrimonioRestClientFactory() {
        return ContaPatrimonioRestClientFactory.builder()
                .protocol(protocol)
                .servername(servername)
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .categoriaRestClientFactory(categoriaRestClientFactory())
                .build();
    }

    private ValorInicialDoDonoNaContaPatrimonioRestClient valorInicialDoDonoNaContaPatrimonioRestClient() {
        return ValorInicialDoDonoNaContaPatrimonioRestClientFactory.builder()
                .protocol(protocol)
                .servername(servername)
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .build().valorInicialDoDonoNaContaPatrimonioRestClient();
    }

    @Test
    public void salvarUm() {        
        // executa a operacao a ser testada
        String mamae = MAMAE;
        String poupanca = POUPANCA;
        String banco = BANCO;

        ValorInicialDoDonoNaContaPatrimonioDTO dono = ValorInicialDoDonoNaContaPatrimonioDTO.builder()
                .valorInicial(new BigDecimal(1000))
                .dono(donoRestClientFactory().dono(mamae))
                .contaPatrimonio(contaPatrimonioRestClientFactory().contaPatrimonio(poupanca, banco))
                .build();

        // verifica a operacao save
        assertThat(valorInicialDoDonoNaContaPatrimonioRestClient().save(dono))
        	.isNotNull();
        
        // verifica a operacao findAll
        assertThat(valorInicialDoDonoNaContaPatrimonioRestClient().findAll())
                .isNotEmpty();        
    }

}
