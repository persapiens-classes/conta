package br.edu.ifrn.conta.persistence;

import br.edu.ifrn.conta.domain.Categoria;

import org.springframework.data.repository.CrudRepository;

/**
 * Categoria Repository with method definition.
 */
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

	Categoria findByDescricao(String descricao);

}
