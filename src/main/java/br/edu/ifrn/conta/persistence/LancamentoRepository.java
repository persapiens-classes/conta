package br.edu.ifrn.conta.persistence;

import br.edu.ifrn.conta.domain.ContaPatrimonio;
import br.edu.ifrn.conta.domain.Dono;
import br.edu.ifrn.conta.domain.Lancamento;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Lancamento Repository Implementation with Query.
 */
@RepositoryRestResource(collectionResourceRel = "lancamentos", path = "lancamentos")
public interface LancamentoRepository extends CrudRepository<Lancamento, Long> {
    @Query("SELECT SUM(l.valor) as valor FROM Lancamento l WHERE l.dono = ?1 and l.contaEntrada = ?2")
    LancamentoSum creditosSum(Dono dono, ContaPatrimonio contaPatrimonio);

    @Query("SELECT SUM(l.valor) as valor FROM Lancamento l WHERE l.dono = ?1 and l.contaSaida = ?2")
    LancamentoSum debitosSum(Dono dono, ContaPatrimonio contaPatrimonio);
}
