package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.Dono;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DonoRestClient {

    private RestClientHelper<Dono> entityRestHelper;

    public void deleteByDescricao(String descricao) {
        entityRestHelper.deleteByDescricao(descricao);
    }

    public Dono findByDescricao(String descricao) {
        return this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findByDescricaoUri(descricao), Dono.class);
    }

    public Iterable<Dono> findAll() {
        return this.entityRestHelper.findAll();
    }

    public Dono save(Dono entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, Dono.class);
    }

}
