package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.dto.CategoriaDTO;
import java.util.Optional;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.experimental.SuperBuilder;
import lombok.Data;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@Data
@SuperBuilder
public class CategoriaRestClient {

    private RestClientHelper<CategoriaDTO> entityRestHelper;

    public Optional<CategoriaDTO> findByDescricao(String descricao) {
        return Optional.ofNullable(this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findByDescricaoUri(descricao), CategoriaDTO.class));
    }

    public Iterable<CategoriaDTO> findAll() {
        return this.entityRestHelper.findAll();
    }

    public CategoriaDTO save(CategoriaDTO entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, CategoriaDTO.class);
    }

}
