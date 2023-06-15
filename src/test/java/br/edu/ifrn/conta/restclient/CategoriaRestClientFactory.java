package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.controller.CategoriaDTO;
import lombok.experimental.SuperBuilder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@SuperBuilder
@Data
public class CategoriaRestClientFactory {

    private String protocol;
    
    private String servername;

    private int port;

    private RestTemplate restTemplate;

    public CategoriaRestClient categoriaRestClient() {
        return CategoriaRestClient.builder()
                .entityRestHelper(RestClientHelper.<CategoriaDTO>builder()
                    .endpoint("categoria")
                    .protocol(protocol)
                    .servername(servername)
                    .port(port)
                    .restTemplate(restTemplate)
                    .build())
                .build();
    }

    public CategoriaDTO categoria(String descricao) {
        CategoriaDTO result = categoriaRestClient().findByDescricao(descricao);
        if (result == null) {
            result = CategoriaDTO.builder().descricao(descricao).build();
            result = categoriaRestClient().save(result);
        }
        
        return result;
    }

}
