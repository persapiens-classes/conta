package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.Lancamento;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LancamentoRestClient {

    private RestClientHelper<Lancamento> entityRestHelper;

    public void deleteByDescricao(String descricao) {
        entityRestHelper.deleteByDescricao(descricao);
    }

    public Lancamento findByDescricao(String descricao) {
        return this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findByDescricaoUrl(descricao), Lancamento.class);
    }

    public Iterable<Lancamento> findAll() {
        return this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findAllUrl(), LancamentoDTO.class).get_embedded().getLancamentos();
    }

    public Lancamento save(Lancamento entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUrl(), entity, Lancamento.class);
    }

}
