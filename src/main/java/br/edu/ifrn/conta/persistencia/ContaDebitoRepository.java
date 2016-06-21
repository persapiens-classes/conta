package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.ContaDebito;

public interface ContaDebitoRepository extends ContaRepository<ContaDebito, Long> {
    
    ContaDebito findByDescricao(String descricao);

}
