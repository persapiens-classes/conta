package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.controller.ValorInicialDoDonoNaContaPatrimonioDTO;
import lombok.experimental.SuperBuilder;
import lombok.Data;

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
