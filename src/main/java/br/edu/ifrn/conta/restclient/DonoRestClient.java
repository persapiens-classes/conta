package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.dto.DonoDTO;
import java.util.Optional;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.experimental.SuperBuilder;
import lombok.Data;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@Data
@SuperBuilder
public class DonoRestClient {

    private RestClientHelper<DonoDTO> entityRestHelper;

    public void deleteByDescricao(String descricao) {
        entityRestHelper.deleteByDescricao(descricao);
    }

    public Optional<DonoDTO> findByDescricao(String descricao) {
        return Optional.ofNullable(this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findByDescricaoUri(descricao), DonoDTO.class));
    }

    public Iterable<DonoDTO> findAll() {
        return this.entityRestHelper.findAll();
    }

    public DonoDTO save(DonoDTO entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, DonoDTO.class);
    }

}
