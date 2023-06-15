package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.controller.DonoDTO;
import lombok.experimental.SuperBuilder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@SuperBuilder
@Data
public class DonoRestClientFactory {

    private String protocol;
    
    private String servername;

    private int port;

    private RestTemplate restTemplate;

    public DonoRestClient donoRestClient() {
        return DonoRestClient.builder()
                .entityRestHelper(RestClientHelper.<DonoDTO>builder()
                    .endpoint("dono")
                    .protocol(protocol)
                    .servername(servername)
                    .port(port)
                    .restTemplate(restTemplate)
                    .build())
                .build();
    }
    
    public DonoDTO dono(String descricao) {
        DonoDTO result = donoRestClient().findByDescricao(descricao);
        if (result == null) {
            result = DonoDTO.builder().descricao(descricao).build();
            result = donoRestClient().save(result);
        }
        
        return result;
    }

}
