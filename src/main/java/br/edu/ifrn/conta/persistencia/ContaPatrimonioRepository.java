package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * Usando Query By Example
 */
public interface ContaPatrimonioRepository extends CrudRepository<ContaPatrimonio, Long>, QueryByExampleExecutor<ContaPatrimonio> {

}
