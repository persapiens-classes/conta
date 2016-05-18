package br.edu.ifrn.conta.servico;

import br.edu.ifrn.conta.dominio.Dono;
import java.util.Iterator;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.ifrn.conta.persistencia.DonoRepositorio;

@Named
public class DonoServico {
    
    private DonoRepositorio repositorio;

    @Inject 
    public DonoServico(DonoRepositorio repositorio) {
        this.repositorio = repositorio;
    }
    
    public void save(Dono objeto) {
        // realizar verificacoes de negocio        
        
        // delega a persistencia para o repositorio
        repositorio.save(objeto);
    }
    
    public void delete(Dono objeto) {
        // realizar verificacoes de negocio
                
        // delega a persistencia para o repositorio
        repositorio.delete(objeto);
    }
    
    public Iterator<Dono> iterator() {
        return repositorio.iterator();
    }
    
}
