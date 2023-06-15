package br.edu.ifrn.conta.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.domain.ContaCredito;
import br.edu.ifrn.conta.domain.ContaDebito;
import br.edu.ifrn.conta.domain.ContaPatrimonio;
import br.edu.ifrn.conta.domain.Dono;
import br.edu.ifrn.conta.domain.Lancamento;
import br.edu.ifrn.conta.domain.ValorInicialDoDonoNaContaPatrimonio;
import br.edu.ifrn.conta.persistence.CategoriaFactory;
import br.edu.ifrn.conta.persistence.ContaCreditoFactory;
import br.edu.ifrn.conta.persistence.ContaDebitoFactory;
import br.edu.ifrn.conta.persistence.ContaPatrimonioFactory;
import br.edu.ifrn.conta.restclient.CategoriaRestClientFactory;
import br.edu.ifrn.conta.restclient.ContaCreditoRestClientFactory;
import br.edu.ifrn.conta.restclient.ContaDebitoRestClientFactory;
import br.edu.ifrn.conta.restclient.ContaPatrimonioRestClientFactory;
import br.edu.ifrn.conta.restclient.DonoRestClientFactory;
import br.edu.ifrn.conta.restclient.LancamentoControllerRestClient;
import br.edu.ifrn.conta.restclient.LancamentoRestClient;
import br.edu.ifrn.conta.restclient.LancamentoRestClientFactory;
import br.edu.ifrn.conta.restclient.ValorInicialDoDonoNaContaPatrimonioRestClient;
import br.edu.ifrn.conta.restclient.ValorInicialDoDonoNaContaPatrimonioRestClientFactory;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LancamentoControllerIT {

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

    private ContaDebitoRestClientFactory contaDebitoRestClientFactory() {
        return ContaDebitoRestClientFactory.builder()
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

    private ValorInicialDoDonoNaContaPatrimonioRestClient valorInicialDoDonoNaContaPatrimonioRestClient() {
        return ValorInicialDoDonoNaContaPatrimonioRestClientFactory.builder()
                .protocol(protocol)
                .servername(servername)
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .build()
                .valorInicialDoDonoNaContaPatrimonioRestClient();
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

    private LancamentoControllerRestClient lancamentoControllerRestClient() {
        return LancamentoControllerRestClient.builder()
                .url(protocol + "://" + servername + ":" + port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .build();
    }

    @Test
    public void saldo500() {
        Dono titio = donoRestClientFactory().dono("titio");
        ContaPatrimonio poupanca = contaPatrimonioRestClientFactory().contaPatrimonio(
    ContaPatrimonioFactory.POUPANCA, CategoriaFactory.BANCO);

        // valor inicial 100
        ValorInicialDoDonoNaContaPatrimonio valorInicial = ValorInicialDoDonoNaContaPatrimonio.builder()
                .contaPatrimonio(poupanca)
                .dono(titio)
                .valorInicial(new BigDecimal(100))
                .build();
        valorInicialDoDonoNaContaPatrimonioRestClient().save(valorInicial);
        
        // creditou 600
        ContaCredito estagio = contaCreditoRestClientFactory().contaCredito(
    ContaCreditoFactory.ESTAGIO, CategoriaFactory.SALARIO);        
        Lancamento lancamentoCredito = Lancamento.builder()
                .valor(new BigDecimal(600))
                .data(LocalDateTime.now())
                .dono(titio)
                .contaEntrada(poupanca)
                .contaSaida(estagio)
                .build();
        lancamentoRestClient().save(lancamentoCredito);

        // debitou 200
        ContaDebito gasolina = contaDebitoRestClientFactory().contaDebito(
    ContaDebitoFactory.GASOLINA, CategoriaFactory.TRANSPORTE);
        Lancamento lancamentoDebito = Lancamento.builder()
                .valor(new BigDecimal(200))
                .data(LocalDateTime.now())
                .dono(titio)
                .contaEntrada(gasolina)
                .contaSaida(poupanca)
                .build();
        lancamentoRestClient().save(lancamentoDebito);
        
        // executa a operacao a ser testada
        BigDecimal saldo = lancamentoControllerRestClient().saldo(
        titio.getDescricao(), poupanca.getDescricao());
        
        assertThat(saldo).isEqualTo(new BigDecimal(500));
    }
}
