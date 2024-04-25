package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dto.ContaCreditoDTO;
import br.edu.ifrn.conta.dto.ContaDebitoDTO;
import br.edu.ifrn.conta.dto.ContaPatrimonioDTO;
import br.edu.ifrn.conta.dto.DonoDTO;
import br.edu.ifrn.conta.dto.LancamentoDTO;
import br.edu.ifrn.conta.dto.ValorInicialDoDonoNaContaPatrimonioDTO;
import static br.edu.ifrn.conta.util.CategoriaConstants.BANCO;
import static br.edu.ifrn.conta.util.CategoriaConstants.SALARIO;
import static br.edu.ifrn.conta.util.CategoriaConstants.TRANSPORTE;
import static br.edu.ifrn.conta.util.ContaCreditoConstants.ESTAGIO;
import static br.edu.ifrn.conta.util.ContaDebitoConstants.GASOLINA;
import static br.edu.ifrn.conta.util.ContaPatrimonioConstants.POUPANCA;
import static br.edu.ifrn.conta.util.DonoConstants.TITIO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SaldoRestClientIT {

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

    private ContaDebitoRestClientFactory contaDebitoRestClientFactory() {
        return ContaDebitoRestClientFactory.builder()
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

    private SaldoRestClient saldoRestClient() {
        return SaldoRestClientFactory.builder()
                .protocol(protocol)
                .servername(servername)
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .build()
                .saldoRestClient();
    }

    private ValorInicialDoDonoNaContaPatrimonioRestClient valorInicialDoDonoNaContaPatrimonioRestClient() {
        return ValorInicialDoDonoNaContaPatrimonioRestClientFactory.builder()
                .protocol(protocol)
                .servername(servername)
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .build()
                .valorInicialDoDonoNaContaPatrimonioRestClient();
    }

    @Test
    public void saldo500() {
        DonoDTO titio = donoRestClientFactory().dono(TITIO);
        ContaPatrimonioDTO poupanca = contaPatrimonioRestClientFactory().contaPatrimonio(
    POUPANCA, BANCO);

        // valor inicial 100
        ValorInicialDoDonoNaContaPatrimonioDTO valorInicial = ValorInicialDoDonoNaContaPatrimonioDTO.builder()
                .contaPatrimonio(poupanca)
                .dono(titio)
                .valorInicial(new BigDecimal(100))
                .build();
        valorInicialDoDonoNaContaPatrimonioRestClient().save(valorInicial);
        
        // creditou 600
        ContaCreditoDTO estagio = contaCreditoRestClientFactory().contaCredito(
    ESTAGIO, SALARIO);        
        LancamentoDTO lancamentoCredito = LancamentoDTO.builder()
                .valor(new BigDecimal(600))
                .data(LocalDateTime.now())
                .dono(titio)
                .contaEntrada(poupanca)
                .contaSaida(estagio)
                .build();
        lancamentoRestClient().save(lancamentoCredito);

        // debitou 200
        ContaDebitoDTO gasolina = contaDebitoRestClientFactory().contaDebito(
    GASOLINA, TRANSPORTE);
        LancamentoDTO lancamentoDebito = LancamentoDTO.builder()
                .valor(new BigDecimal(200))
                .data(LocalDateTime.now())
                .dono(titio)
                .contaEntrada(gasolina)
                .contaSaida(poupanca)
                .build();
        lancamentoRestClient().save(lancamentoDebito);
        
        // executa a operacao a ser testada
        BigDecimal saldo = saldoRestClient().saldo(
        titio.getDescricao(), poupanca.getDescricao());
        
        assertThat(saldo).isEqualTo(new BigDecimal(500).setScale(2));
    }

}
