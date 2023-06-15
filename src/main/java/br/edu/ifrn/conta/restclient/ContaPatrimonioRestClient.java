package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.ContaPatrimonio;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContaPatrimonioRestClient {

    private RestClientHelper<ContaPatrimonio> entityRestHelper;

    public void deleteByDescricao(String descricao) {
        entityRestHelper.deleteByDescricao(descricao);
    }

    public ContaPatrimonio findByDescricao(String descricao) {
        return this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findByDescricaoUri(descricao), ContaPatrimonio.class);
    }

    public Iterable<ContaPatrimonio> findAll() {
        return this.entityRestHelper.findAll();
    }

    public ContaPatrimonio save(ContaPatrimonio entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, ContaPatrimonio.class);
    }

}