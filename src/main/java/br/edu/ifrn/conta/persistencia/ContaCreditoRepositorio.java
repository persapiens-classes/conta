package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.ContaCredito;
import java.util.Iterator;

public interface ContaCreditoRepositorio {

    void save(ContaCredito objeto);

    void delete(ContaCredito objeto);
    
    Iterator<ContaCredito> iterator();
    
}
