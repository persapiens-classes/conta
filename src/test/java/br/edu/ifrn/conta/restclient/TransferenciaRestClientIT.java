package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.controller.ContaPatrimonioDTO;
import br.edu.ifrn.conta.controller.DonoDTO;
import br.edu.ifrn.conta.controller.TransferenciaDTO;
import br.edu.ifrn.conta.persistence.CategoriaFactory;
import br.edu.ifrn.conta.persistence.ContaPatrimonioFactory;
import br.edu.ifrn.conta.persistence.DonoFactory;
import java.math.BigDecimal;
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
public class TransferenciaRestClientIT {

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

    private TransferenciaRestClient transferenciaRestClient() {
        return TransferenciaRestClientFactory.builder()
                .protocol(protocol)
                .servername(servername)
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .build()
                .transferenciaRestClient();
    }

    @Test
    public void transferir50DeContaCorrentePapaiParaContaInvestimentoTitio() {
        DonoDTO papai = donoRestClientFactory().dono(DonoFactory.PAPAI);
        DonoDTO titio = donoRestClientFactory().dono(DonoFactory.TITIO);

        ContaPatrimonioDTO contaCorrente = contaPatrimonioRestClientFactory().contaPatrimonio(
    ContaPatrimonioFactory.CONTA_CORRENTE, CategoriaFactory.BANCO);
        ContaPatrimonioDTO contaInvestimento = contaPatrimonioRestClientFactory().contaPatrimonio(
    ContaPatrimonioFactory.CONTA_INVESTIMENTO, CategoriaFactory.BANCO);
        
        // executa a operacao a ser testada
        transferenciaRestClient().transferir(TransferenciaDTO.builder()
            .donoDebito(papai)
            .donoCredito(titio)
            .contaDebito(contaCorrente)
            .contaCredito(contaInvestimento)
            .valor(new BigDecimal(50))
            .build());
        
        assertThat(lancamentoRestClient().debitosSum(papai.getDescricao(), contaCorrente.getDescricao()))
            .isEqualTo(new BigDecimal(50).setScale(2));
        
        assertThat(lancamentoRestClient().creditosSum(titio.getDescricao(), contaInvestimento.getDescricao()))
            .isEqualTo(new BigDecimal(50).setScale(2));
    }

}