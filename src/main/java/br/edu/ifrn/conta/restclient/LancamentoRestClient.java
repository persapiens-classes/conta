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
    
}
