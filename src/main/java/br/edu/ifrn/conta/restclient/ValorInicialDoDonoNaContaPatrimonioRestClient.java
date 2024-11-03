package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.dto.ValorInicialDoDonoNaContaPatrimonioDTO;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.experimental.SuperBuilder;
import lombok.Data;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@Data
@SuperBuilder
public class ValorInicialDoDonoNaContaPatrimonioRestClient {

    private RestClientHelper<ValorInicialDoDonoNaContaPatrimonioDTO> entityRestHelper;

    public Iterable<ValorInicialDoDonoNaContaPatrimonioDTO> findAll() {
        return this.entityRestHelper.findAll();
    }

    public ValorInicialDoDonoNaContaPatrimonioDTO save(ValorInicialDoDonoNaContaPatrimonioDTO entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, ValorInicialDoDonoNaContaPatrimonioDTO.class);
    }

}
