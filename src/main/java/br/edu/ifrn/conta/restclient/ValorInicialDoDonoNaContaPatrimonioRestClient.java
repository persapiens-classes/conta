package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.ValorInicialDoDonoNaContaPatrimonio;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValorInicialDoDonoNaContaPatrimonioRestClient {

    private RestClientHelper<ValorInicialDoDonoNaContaPatrimonio> entityRestHelper;

    public Iterable<ValorInicialDoDonoNaContaPatrimonio> findAll() {
        return this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findAllUri(), ValorInicialDoDonoNaContaPatrimonioDTO.class).get_embedded().getValoresIniciaisDoDonoNaContaPatrimonio();
    }

    public ValorInicialDoDonoNaContaPatrimonio save(ValorInicialDoDonoNaContaPatrimonio entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, ValorInicialDoDonoNaContaPatrimonio.class);
    }

}
