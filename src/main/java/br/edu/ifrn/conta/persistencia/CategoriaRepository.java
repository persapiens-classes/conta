package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.Categoria;
import org.springframework.data.repository.CrudRepository;

/**
 * Usando apenas CrudRepository
 */
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

}
