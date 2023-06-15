package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.controller.ContaPatrimonioDTO;
import lombok.experimental.SuperBuilder;
import lombok.Data;

@Data
@SuperBuilder
public class ContaPatrimonioRestClient {

    private RestClientHelper<ContaPatrimonioDTO> entityRestHelper;

    public void deleteByDescricao(String descricao) {
        entityRestHelper.deleteByDescricao(descricao);
    }

    public ContaPatrimonioDTO findByDescricao(String descricao) {
        return this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findByDescricaoUri(descricao), ContaPatrimonioDTO.class);
    }

    public Iterable<ContaPatrimonioDTO> findAll() {
        return this.entityRestHelper.findAll();
    }

    public ContaPatrimonioDTO save(ContaPatrimonioDTO entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, ContaPatrimonioDTO.class);
    }

}
