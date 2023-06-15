package br.edu.ifrn.conta.persistence;

import br.edu.ifrn.conta.domain.ContaCredito;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Conta Credito Repository.
 */
@RepositoryRestResource(collectionResourceRel = "contasCredito", path = "contasCredito")
public interface ContaCreditoRepository extends ContaRepository<ContaCredito, Long> {

}
