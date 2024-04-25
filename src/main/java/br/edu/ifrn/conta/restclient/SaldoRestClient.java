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
public class SaldoRestClient {

    private RestClientHelper<LancamentoDTO> entityRestHelper;

    public BigDecimal saldo(String dono, String contaPatrimonio) {
        return this.entityRestHelper.getRestTemplate().getForObject(
        UriComponentsBuilder.fromHttpUrl(entityRestHelper.url() + "/saldo")
            .queryParam("dono", dono)
            .queryParam("contaPatrimonio", contaPatrimonio)
            .build().encode().toUri()
            , BigDecimal.class);
    }
    
}
