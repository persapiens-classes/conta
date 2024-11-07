package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.dto.ValorInicialDoDonoNaContaPatrimonioDTO;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.experimental.SuperBuilder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
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
