package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.ContaCredito;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContaCreditoRestClient {

    private RestClientHelper<ContaCredito> entityRestHelper;

    public void deleteByDescricao(String descricao) {
        entityRestHelper.deleteByDescricao(descricao);
    }

    public ContaCredito findByDescricao(String descricao) {
        return this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findByDescricaoUri(descricao), ContaCredito.class);
    }

    public Iterable<ContaCredito> findAll() {
        return this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findAllUri(), ContaCreditoDTO.class).get_embedded().getContasCredito();
    }

    public ContaCredito save(ContaCredito entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, ContaCredito.class);
    }

}
