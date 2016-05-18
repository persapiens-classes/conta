package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.Dono;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import javax.inject.Named;

@Named
public class DonoRepositorioMemoria implements DonoRepositorio {

    private Set<Dono> objetos = new TreeSet<>();
    
    @Override
    public void save(Dono objeto) {
        objetos.add(objeto);
    }

    @Override
    public void delete(Dono objeto) {
        objetos.remove(objeto);
    }

    @Override
    public Iterator<Dono> iterator() {
        return objetos.iterator();
    }
    
}
