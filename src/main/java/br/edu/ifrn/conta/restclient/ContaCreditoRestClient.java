package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.controller.ContaCreditoDTO;
import lombok.experimental.SuperBuilder;
import lombok.Data;

@Data
@SuperBuilder
public class ContaCreditoRestClient {

    private RestClientHelper<ContaCreditoDTO> entityRestHelper;

    public void deleteByDescricao(String descricao) {
        entityRestHelper.deleteByDescricao(descricao);
    }

    public ContaCreditoDTO findByDescricao(String descricao) {
        return this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findByDescricaoUri(descricao), ContaCreditoDTO.class);
    }

    public Iterable<ContaCreditoDTO> findAll() {
        return this.entityRestHelper.findAll();
    }

    public ContaCreditoDTO save(ContaCreditoDTO entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, ContaCreditoDTO.class);
    }

}
