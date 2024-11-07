package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.dto.LancamentoDTO;
import java.math.BigDecimal;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
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

    public BigDecimal creditosSum(String dono, String contaPatrimonio) {
        return this.entityRestHelper.getRestTemplate().getForObject(
        UriComponentsBuilder.fromHttpUrl(entityRestHelper.url() + "/creditosSum")
            .queryParam("dono", dono)
            .queryParam("contaPatrimonio", contaPatrimonio)
            .build().encode().toUri()
            , BigDecimal.class);
    }

    public BigDecimal debitosSum(String dono, String contaPatrimonio) {
        return this.entityRestHelper.getRestTemplate().getForObject(
        UriComponentsBuilder.fromHttpUrl(entityRestHelper.url() + "/debitosSum")
            .queryParam("dono", dono)
            .queryParam("contaPatrimonio", contaPatrimonio)
            .build().encode().toUri()
            , BigDecimal.class);
    }
}
