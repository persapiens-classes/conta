package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.controller.ValorInicialDoDonoNaContaPatrimonioDTO;
import lombok.experimental.SuperBuilder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@SuperBuilder
@Data
public class ValorInicialDoDonoNaContaPatrimonioRestClientFactory {

    private String protocol;
    
    private String servername;

    private int port;

    private RestTemplate restTemplate;

    public ValorInicialDoDonoNaContaPatrimonioRestClient valorInicialDoDonoNaContaPatrimonioRestClient() {
        return ValorInicialDoDonoNaContaPatrimonioRestClient.builder()
                .entityRestHelper(RestClientHelper.<ValorInicialDoDonoNaContaPatrimonioDTO>builder()
                    .endpoint("valorInicialDoDonoNaContaPatrimonio")
                    .protocol(protocol)
                    .servername(servername)
                    .port(port)
                    .restTemplate(restTemplate)
                    .build())
                .build();
    }

}
