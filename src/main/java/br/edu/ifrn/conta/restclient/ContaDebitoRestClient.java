package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.ContaDebito;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContaDebitoRestClient {

    private RestClientHelper<ContaDebito> entityRestHelper;

    public void deleteByDescricao(String descricao) {
        entityRestHelper.deleteByDescricao(descricao);
    }

    public ContaDebito findByDescricao(String descricao) {
        return this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findByDescricaoUri(descricao), ContaDebito.class);
    }

    public Iterable<ContaDebito> findAll() {
        return this.entityRestHelper.findAll();
    }

    public ContaDebito save(ContaDebito entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, ContaDebito.class);
    }

}
