package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import br.edu.ifrn.conta.dominio.Dono;
import java.math.BigDecimal;

/**
 * CrudRepository com definicoes de metodos
 */
public interface LancamentoRepositoryCustom {
    BigDecimal saldo(Dono dono, ContaPatrimonio contaPatrimonio);
}
