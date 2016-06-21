package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.Conta;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * Usando tipos parametrizados
 * Utilizando também Query By Example
 */
public interface ContaRepository <T extends Conta, ID extends Serializable> extends CrudRepository<T, ID>, QueryByExampleExecutor<T> {
    
    T findByDescricao(String descricao);

}
