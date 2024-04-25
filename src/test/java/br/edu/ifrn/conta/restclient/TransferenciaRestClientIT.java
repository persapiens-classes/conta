package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dto.ContaPatrimonioDTO;
import br.edu.ifrn.conta.dto.DonoDTO;
import br.edu.ifrn.conta.dto.TransferenciaDTO;
import static br.edu.ifrn.conta.util.CategoriaConstants.BANCO;
import static br.edu.ifrn.conta.util.ContaPatrimonioConstants.CONTA_CORRENTE;
import static br.edu.ifrn.conta.util.ContaPatrimonioConstants.CONTA_INVESTIMENTO;
import static br.edu.ifrn.conta.util.DonoConstants.TITIA;
import static br.edu.ifrn.conta.util.DonoConstants.TITIO;
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
    public void transferir50DeContaCorrenteTitiaParaContaInvestimentoTitio() {
        DonoDTO titia = donoRestClientFactory().dono(TITIA);
        DonoDTO titio = donoRestClientFactory().dono(TITIO);

        ContaPatrimonioDTO contaCorrente = contaPatrimonioRestClientFactory().contaPatrimonio(
    CONTA_CORRENTE, BANCO);
        ContaPatrimonioDTO contaInvestimento = contaPatrimonioRestClientFactory().contaPatrimonio(
    CONTA_INVESTIMENTO, BANCO);
        
        // executa a operacao a ser testada
        transferenciaRestClient().transferir(TransferenciaDTO.builder()
            .donoDebito(titia.getDescricao())
            .donoCredito(titio.getDescricao())
            .contaDebito(contaCorrente.getDescricao())
            .contaCredito(contaInvestimento.getDescricao())
            .valor(new BigDecimal(50))
            .build());
        
        assertThat(lancamentoRestClient().debitosSum(titia.getDescricao(), contaCorrente.getDescricao()))
            .isEqualTo(new BigDecimal(50).setScale(2));
        
        assertThat(lancamentoRestClient().creditosSum(titio.getDescricao(), contaInvestimento.getDescricao()))
            .isEqualTo(new BigDecimal(50).setScale(2));
    }

}
