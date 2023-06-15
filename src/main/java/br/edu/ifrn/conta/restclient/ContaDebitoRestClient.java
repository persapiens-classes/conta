package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.controller.ContaDebitoDTO;
import lombok.experimental.SuperBuilder;
import lombok.Data;

@Data
@SuperBuilder
public class ContaDebitoRestClient {

    private RestClientHelper<ContaDebitoDTO> entityRestHelper;

    public void deleteByDescricao(String descricao) {
        entityRestHelper.deleteByDescricao(descricao);
    }

    public ContaDebitoDTO findByDescricao(String descricao) {
        return this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findByDescricaoUri(descricao), ContaDebitoDTO.class);
    }

    public Iterable<ContaDebitoDTO> findAll() {
        return this.entityRestHelper.findAll();
    }

    public ContaDebitoDTO save(ContaDebitoDTO entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, ContaDebitoDTO.class);
    }

}
