package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import br.edu.ifrn.conta.dominio.Dono;
import br.edu.ifrn.conta.dominio.ValorInicialDoDonoNaContaPatrimonio;
import org.springframework.data.repository.CrudRepository;

/**
 * CrudRepository com definicoes de metodos
 */
public interface ValorInicialDoDonoNaContaPatrimonioRepository extends CrudRepository<ValorInicialDoDonoNaContaPatrimonio, Long> {
    
    ValorInicialDoDonoNaContaPatrimonio findByDonoAndContaPatrimonio(Dono dono, ContaPatrimonio contaPatrimonio);
    
}
