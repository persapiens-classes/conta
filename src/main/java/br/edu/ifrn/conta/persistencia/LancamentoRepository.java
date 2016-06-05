package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.Lancamento;
import org.springframework.data.repository.CrudRepository;

/**
 * CrudRepository com definicoes de metodos
 */
public interface LancamentoRepository extends CrudRepository<Lancamento, Long>, LancamentoRepositoryCustom {
    
}
