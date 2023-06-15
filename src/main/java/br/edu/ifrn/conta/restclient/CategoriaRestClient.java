package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.Categoria;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaRestClient {

    private RestClientHelper<Categoria> entityRestHelper;

    public Categoria findByDescricao(String descricao) {
        return this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findByDescricaoUri(descricao), Categoria.class);
    }

    public Iterable<Categoria> findAll() {
        return this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findAllUri(), CategoriaDTO.class).get_embedded().getCategorias();
    }

    public Categoria save(Categoria entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, Categoria.class);
    }

}
