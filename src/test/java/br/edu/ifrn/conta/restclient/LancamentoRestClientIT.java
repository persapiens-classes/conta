package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.controller.LancamentoDTO;
import br.edu.ifrn.conta.persistence.ContaCreditoFactory;
import br.edu.ifrn.conta.persistence.DonoFactory;
import br.edu.ifrn.conta.persistence.CategoriaFactory;
import br.edu.ifrn.conta.persistence.ContaPatrimonioFactory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LancamentoRestClientIT {

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

    private DonoRestClientFactory donoRestClientFactory() {
        return DonoRestClientFactory.builder()
                .protocol(protocol)
                .servername(servername)
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .build();
    }

    private ContaCreditoRestClientFactory contaCreditoRestClientFactory() {
        return ContaCreditoRestClientFactory.builder()
                .protocol(protocol)
                .servername(servername)
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .categoriaRestClientFactory(categoriaRestClientFactory())
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

    private LancamentoRestClient lancamentoRestClient() {
        return LancamentoRestClientFactory.builder()
                .protocol(protocol)
                .servername(servername)
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .build()
                .lancamentoRestClient();
    }

    @Test
    public void salvarUm() {        
        // executa a operacao a ser testada
        String mamae = DonoFactory.MAMAE;
        String estagio = ContaCreditoFactory.ESTAGIO;
        String salario = CategoriaFactory.SALARIO;
        String poupanca = ContaPatrimonioFactory.POUPANCA;
        String banco = CategoriaFactory.BANCO;
        
        LancamentoDTO lancamento = LancamentoDTO.builder()
                .valor(new BigDecimal(543))
                .data(LocalDateTime.now())
                .descricao("Teste de Sistema")
                .dono(donoRestClientFactory().dono(mamae))
                .contaEntrada(contaPatrimonioRestClientFactory().contaPatrimonio(poupanca, banco))
                .contaSaida(contaCreditoRestClientFactory().contaCredito(estagio, salario))
                .build();

        // verifica a operacao save
        assertThat(lancamentoRestClient().save(lancamento))
        	.isNotNull();
        
        // verifica a operacao findAll
        assertThat(lancamentoRestClient().findAll())
                .isNotEmpty();        
    }

}
