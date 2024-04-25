package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.dto.ContaPatrimonioDTO;
import java.util.Optional;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.experimental.SuperBuilder;
import lombok.Data;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@Data
@SuperBuilder
public class ContaPatrimonioRestClient {

    private RestClientHelper<ContaPatrimonioDTO> entityRestHelper;

    public void deleteByDescricao(String descricao) {
        entityRestHelper.deleteByDescricao(descricao);
    }

    public Optional<ContaPatrimonioDTO> findByDescricao(String descricao) {
        return Optional.ofNullable(this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findByDescricaoUri(descricao), ContaPatrimonioDTO.class));
    }

    public Iterable<ContaPatrimonioDTO> findAll() {
        return this.entityRestHelper.findAll();
    }

    public ContaPatrimonioDTO save(ContaPatrimonioDTO entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, ContaPatrimonioDTO.class);
    }

}
