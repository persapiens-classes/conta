package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.Categoria;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Data
@Builder
public class LancamentoControllerRestClient {

    private RestTemplate restTemplate;
    private String url;

    public BigDecimal saldo(String dono, String contaPatrimonio) {
        return this.restTemplate.getForObject(
        UriComponentsBuilder.fromHttpUrl(url + "/saldo")
            .queryParam("dono", dono)
            .queryParam("contaPatrimonio", contaPatrimonio)
            .build().encode().toUri()
            , BigDecimal.class);
    }

}
