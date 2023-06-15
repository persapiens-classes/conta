package br.edu.ifrn.conta.persistence;

import br.edu.ifrn.conta.domain.ContaPatrimonio;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Conta Patrimonio Repository.
 */
@RepositoryRestResource(collectionResourceRel = "contasPatrimonio", path = "contasPatrimonio")
public interface ContaPatrimonioRepository extends ContaRepository<ContaPatrimonio, Long> {
}
