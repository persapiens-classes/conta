package br.edu.ifrn.conta.persistence;

import br.edu.ifrn.conta.domain.ContaPatrimonio;
import br.edu.ifrn.conta.domain.Dono;
import br.edu.ifrn.conta.domain.ValorInicialDoDonoNaContaPatrimonio;

import org.springframework.data.repository.CrudRepository;

/**
 * CrudRepository with method definitions.
 */
public interface ValorInicialDoDonoNaContaPatrimonioRepository extends CrudRepository<ValorInicialDoDonoNaContaPatrimonio, Long> {

    ValorInicialDoDonoNaContaPatrimonio findByDonoAndContaPatrimonio(Dono dono, ContaPatrimonio contaPatrimonio);

}
