package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.dto.ContaCreditoDTO;
import java.util.Optional;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.experimental.SuperBuilder;
import lombok.Data;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@Data
@SuperBuilder
public class ContaCreditoRestClient {

    private RestClientHelper<ContaCreditoDTO> entityRestHelper;

    public void deleteByDescricao(String descricao) {
        entityRestHelper.deleteByDescricao(descricao);
    }

    public Optional<ContaCreditoDTO> findByDescricao(String descricao) {
        return Optional.ofNullable(this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findByDescricaoUri(descricao), ContaCreditoDTO.class));
    }

    public Iterable<ContaCreditoDTO> findAll() {
        return this.entityRestHelper.findAll();
    }

    public ContaCreditoDTO save(ContaCreditoDTO entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, ContaCreditoDTO.class);
    }

}
