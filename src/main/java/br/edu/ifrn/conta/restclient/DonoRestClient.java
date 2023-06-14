package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.Dono;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@Data
@Builder
public class DonoRestClient {

    private String protocol;
    private String servername;
    private int port;
    private RestTemplate restTemplate;

    private String url() {
        return protocol + "://" + servername + ":" + port + "/donos";
    }

    public void deleteByDescricao(String descricao) {
        this.restTemplate.exchange(url() + "/search/deleteByDescricao?descricao=" + descricao, HttpMethod.GET, null, Dono.class);
    }

    public Dono findByDescricao(String descricao) {
        return this.restTemplate.getForObject(url() + "/search/findByDescricao?descricao=" + descricao, Dono.class);
    }

    public Iterable<Dono> findAll() {
        return this.restTemplate.getForObject(url(), DonoDTO.class).get_embedded().getDonos();
    }

    public Dono save(Dono dono) {
        return this.restTemplate.postForObject(url(), dono, Dono.class);
    }

}
