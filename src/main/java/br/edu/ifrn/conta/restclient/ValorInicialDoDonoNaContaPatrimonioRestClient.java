package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.ValorInicialDoDonoNaContaPatrimonio;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValorInicialDoDonoNaContaPatrimonioRestClient {

    private RestClientHelper<ValorInicialDoDonoNaContaPatrimonio> entityRestHelper;

    public void deleteByDescricao(String descricao) {
        entityRestHelper.deleteByDescricao(descricao);
    }

    public ValorInicialDoDonoNaContaPatrimonio findByDescricao(String descricao) {
        return this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findByDescricaoUrl(descricao), ValorInicialDoDonoNaContaPatrimonio.class);
    }

    public Iterable<ValorInicialDoDonoNaContaPatrimonio> findAll() {
        return this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findAllUrl(), ValorInicialDoDonoNaContaPatrimonioDTO.class).get_embedded().getValoresIniciaisDoDonoNaContaPatrimonio();
    }

    public ValorInicialDoDonoNaContaPatrimonio save(ValorInicialDoDonoNaContaPatrimonio entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUrl(), entity, ValorInicialDoDonoNaContaPatrimonio.class);
    }

}
