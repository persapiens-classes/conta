package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.ContaCredito;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import javax.inject.Named;

@Named
public class ContaCreditoRepositorioMemoria implements ContaCreditoRepositorio {

    private Set<ContaCredito> objetos = new TreeSet<>();
    
    @Override
    public void save(ContaCredito objeto) {
        objetos.add(objeto);
    }

    @Override
    public void delete(ContaCredito objeto) {
        objetos.remove(objeto);
    }

    @Override
    public Iterator<ContaCredito> iterator() {
        return objetos.iterator();
    }
    
}
