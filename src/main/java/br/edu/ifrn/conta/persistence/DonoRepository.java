package br.edu.ifrn.conta.persistence;

import br.edu.ifrn.conta.domain.Dono;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * CrudRepository with method definitions.
 */
@RepositoryRestResource(collectionResourceRel = "donos", path = "donos")
public interface DonoRepository extends CrudRepository<Dono, Long> {

	Dono findByDescricao(String descricao);

	long countByDescricaoContains(String descricao);

	@Transactional
	void deleteByDescricao(String descricao);

}
