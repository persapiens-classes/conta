package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.ContaDebito;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * Usando Query By Example
 */
public interface ContaDebitoRepository extends CrudRepository<ContaDebito, Long>, QueryByExampleExecutor<ContaDebito> {

}
