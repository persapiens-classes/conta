package br.edu.ifrn.conta.restclient;

import lombok.Builder;
import lombok.Data;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

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

    public void deleteByDescricao(String descricao) {
        this.restTemplate.exchange(url() + "/search/deleteByDescricao?descricao=" + descricao, 
        HttpMethod.GET, null, new ParameterizedTypeReference<T>(){});
    }

    public String findByDescricaoUrl(String descricao) {
        return url() + "/search/findByDescricao?descricao=" + descricao;
    }

    public String findAllUrl() {
        return url();
    }

    public String saveUrl() {
        return url();
    }

}
