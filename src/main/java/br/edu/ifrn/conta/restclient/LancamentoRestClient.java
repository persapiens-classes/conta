package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.controller.LancamentoDTO;
import java.math.BigDecimal;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;

@Data
@SuperBuilder
public class LancamentoRestClient {

    private RestClientHelper<LancamentoDTO> entityRestHelper;

    public Iterable<LancamentoDTO> findAll() {
        return this.entityRestHelper.findAll();
    }

    public LancamentoDTO save(LancamentoDTO entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, LancamentoDTO.class);
    }

    public BigDecimal saldo(String dono, String contaPatrimonio) {
        System.out.println("Chamou SALDO " + entityRestHelper.url() + "/saldo" + dono + "-" + contaPatrimonio);
        return this.entityRestHelper.getRestTemplate().getForObject(
        UriComponentsBuilder.fromHttpUrl(entityRestHelper.url() + "/saldo")
            .queryParam("dono", dono)
            .queryParam("contaPatrimonio", contaPatrimonio)
            .build().encode().toUri()
            , BigDecimal.class);
    }
    
}
