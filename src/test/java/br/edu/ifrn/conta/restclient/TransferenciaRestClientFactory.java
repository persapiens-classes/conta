package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.dto.TransferenciaDTO;
import lombok.experimental.SuperBuilder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@SuperBuilder
@Data
public class TransferenciaRestClientFactory {

    private String protocol;
    
    private String servername;
    
    private int port;

    private RestTemplate restTemplate;

    public TransferenciaRestClient transferenciaRestClient() {
        return TransferenciaRestClient.builder()
                .entityRestHelper(RestClientHelper.<TransferenciaDTO>builder()
                    .endpoint("")
                    .protocol(protocol)
                    .servername(servername)
                    .port(port)
                    .restTemplate(restTemplate)
                    .build())
                .build();
    }

}
