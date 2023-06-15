package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.ValorInicialDoDonoNaContaPatrimonio;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValorInicialDoDonoNaContaPatrimonioRestClient {

    private RestClientHelper<ValorInicialDoDonoNaContaPatrimonio> entityRestHelper;

    public Iterable<ValorInicialDoDonoNaContaPatrimonio> findAll() {
        return this.entityRestHelper.findAll();
    }

    public ValorInicialDoDonoNaContaPatrimonio save(ValorInicialDoDonoNaContaPatrimonio entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, ValorInicialDoDonoNaContaPatrimonio.class);
    }

}
