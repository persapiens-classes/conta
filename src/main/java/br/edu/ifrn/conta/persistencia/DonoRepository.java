package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.Dono;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * CrudRepository com definicoes de metodos
 */
public interface DonoRepository extends CrudRepository<Dono, Long> {
    
    Dono findByDescricao(String descricao);
    
    long countByDescricaoContains(String descricao);
    
    @Transactional
    void deleteByDescricao(String descricao);
    
}
