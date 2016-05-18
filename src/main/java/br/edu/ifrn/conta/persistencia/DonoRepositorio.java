package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.Dono;
import java.util.Iterator;

public interface DonoRepositorio {

    void save(Dono objeto);

    void delete(Dono objeto);
    
    Iterator<Dono> iterator();
    
}
