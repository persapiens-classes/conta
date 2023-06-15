package br.edu.ifrn.conta.restclient;

import java.net.URI;
import lombok.Builder;
import lombok.Data;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Data
@Builder
public class RestClientHelper <T> {

    private String endpoint;
    private String protocol;
    private String servername;
    private int port;
    private RestTemplate restTemplate;

    private String url() {
        return protocol + "://" + servername + ":" + port + "/" + endpoint;
    }

    private URI uri() {
        return UriComponentsBuilder.fromHttpUrl(url())
            .build().encode().toUri();
    }
    
    private URI uri(String suffix, String param, String value) {
        return UriComponentsBuilder.fromHttpUrl(url() + suffix)
            .queryParam(param, value)
            .build().encode().toUri();
    }
    
    public void deleteByDescricao(String descricao) {
        this.restTemplate.exchange(uri("/search/deleteByDescricao", "descricao", descricao), 
        HttpMethod.GET, null, new ParameterizedTypeReference<T>(){});
    }

    public URI findByDescricaoUri(String descricao) {
        return uri("/search/findByDescricao", "descricao", descricao);
    }

    public URI findAllUri() {
        return uri();
    }

    public URI saveUri() {
        return uri();
    }

}
