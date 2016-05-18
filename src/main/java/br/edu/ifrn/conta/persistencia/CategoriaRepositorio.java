package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.Categoria;
import java.util.Iterator;

public interface CategoriaRepositorio {

    void save(Categoria objeto);

    void delete(Categoria objeto);
    
    Iterator<Categoria> iterator();
    
}
