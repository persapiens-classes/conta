package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.Lancamento;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LancamentoRestClient {

    private RestClientHelper<Lancamento> entityRestHelper;

    public Iterable<Lancamento> findAll() {
        return this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findAllUri(), LancamentoDTO.class).get_embedded().getLancamentos();
    }

    public Lancamento save(Lancamento entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, Lancamento.class);
    }

}
