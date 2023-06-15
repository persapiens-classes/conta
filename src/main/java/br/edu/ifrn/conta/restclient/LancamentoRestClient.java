package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.Lancamento;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;

@Data
@Builder
public class LancamentoRestClient {

    private RestClientHelper<Lancamento> entityRestHelper;

    public Iterable<Lancamento> findAll() {
        return this.entityRestHelper.findAll();
    }

    public Lancamento save(Lancamento entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, Lancamento.class);
    }

    public BigDecimal saldo(String dono, String contaPatrimonio) {
        return this.entityRestHelper.getRestTemplate().getForObject(
        UriComponentsBuilder.fromHttpUrl(entityRestHelper.url() + "/saldo")
            .queryParam("dono", dono)
            .queryParam("contaPatrimonio", contaPatrimonio)
            .build().encode().toUri()
            , BigDecimal.class);
    }
    
}
