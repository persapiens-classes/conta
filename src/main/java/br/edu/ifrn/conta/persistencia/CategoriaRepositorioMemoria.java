package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.Categoria;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import javax.inject.Named;

@Named
public class CategoriaRepositorioMemoria implements CategoriaRepositorio {

    private Set<Categoria> objetos = new TreeSet<>();
    
    @Override
    public void save(Categoria objeto) {
        objetos.add(objeto);
    }

    @Override
    public void delete(Categoria objeto) {
        objetos.remove(objeto);
    }

    @Override
    public Iterator<Categoria> iterator() {
        return objetos.iterator();
    }
    
}
