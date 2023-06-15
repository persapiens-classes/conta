package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.ValorInicialDoDonoNaContaPatrimonio;
import lombok.Builder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@Builder
@Data
public class ValorInicialDoDonoNaContaPatrimonioRestClientFactory {

    private String protocol;
    
    private String servername;

    private int port;

    private RestTemplate restTemplate;

    public ValorInicialDoDonoNaContaPatrimonioRestClient valorInicialDoDonoNaContaPatrimonioRestClient() {
        return ValorInicialDoDonoNaContaPatrimonioRestClient.builder()
                .entityRestHelper(RestClientHelper.<ValorInicialDoDonoNaContaPatrimonio>builder()
                    .endpoint("valoresIniciaisDoDonoNaContaPatrimonio")
                    .protocol(protocol)
                    .servername(servername)
                    .port(port)
                    .restTemplate(restTemplate)
                    .build())
                .build();
    }

}
