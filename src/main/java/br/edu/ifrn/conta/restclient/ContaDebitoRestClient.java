package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.dto.ContaDebitoDTO;
import java.util.Optional;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.experimental.SuperBuilder;
import lombok.Data;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@Data
@SuperBuilder
public class ContaDebitoRestClient {

    private RestClientHelper<ContaDebitoDTO> entityRestHelper;

    public void deleteByDescricao(String descricao) {
        entityRestHelper.deleteByDescricao(descricao);
    }

    public Optional<ContaDebitoDTO> findByDescricao(String descricao) {
        return Optional.ofNullable(this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findByDescricaoUri(descricao), ContaDebitoDTO.class));
    }

    public Iterable<ContaDebitoDTO> findAll() {
        return this.entityRestHelper.findAll();
    }

    public ContaDebitoDTO save(ContaDebitoDTO entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, ContaDebitoDTO.class);
    }

}
