package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.ContaCredito;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * Usando Query By Example
 */
public interface ContaCreditoRepository extends CrudRepository<ContaCredito, Long>, QueryByExampleExecutor<ContaCredito> {

}
