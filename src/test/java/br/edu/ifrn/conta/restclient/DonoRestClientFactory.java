package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.Dono;
import lombok.Builder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@Builder
@Data
public class DonoRestClientFactory {

    private String protocol;
    
    private String servername;

    private int port;

    private RestTemplate restTemplate;

    public DonoRestClient donoRestClient() {
        return DonoRestClient.builder()
                .entityRestHelper(RestClientHelper.<Dono>builder()
                    .endpoint("donos")
                    .protocol(protocol)
                    .servername(servername)
                    .port(port)
                    .restTemplate(restTemplate)
                    .build())
                .build();
    }
    
    public Dono dono(String descricao) {
        Dono result = donoRestClient().findByDescricao(descricao);
        if (result == null) {
            result = Dono.builder().descricao(descricao).build();
            result = donoRestClient().save(result);
        }
        
        return result;
    }

}
