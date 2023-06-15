package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.controller.CategoriaDTO;
import lombok.experimental.SuperBuilder;
import lombok.Data;

@Data
@SuperBuilder
public class CategoriaRestClient {

    private RestClientHelper<CategoriaDTO> entityRestHelper;

    public CategoriaDTO findByDescricao(String descricao) {
        return this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findByDescricaoUri(descricao), CategoriaDTO.class);
    }

    public Iterable<CategoriaDTO> findAll() {
        return this.entityRestHelper.findAll();
    }

    public CategoriaDTO save(CategoriaDTO entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, CategoriaDTO.class);
    }

}
