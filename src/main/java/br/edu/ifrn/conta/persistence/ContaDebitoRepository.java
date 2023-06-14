package br.edu.ifrn.conta.persistence;

import br.edu.ifrn.conta.domain.ContaDebito;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Conta Debito repository. Using method definition.
 */
@RepositoryRestResource(collectionResourceRel = "contasDebito", path = "contasDebito")
public interface ContaDebitoRepository extends ContaRepository<ContaDebito, Long> {
}
