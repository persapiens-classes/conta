package br.edu.ifrn.conta.servico;

import br.edu.ifrn.conta.dominio.ContaCredito;
import java.util.Iterator;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.ifrn.conta.persistencia.ContaCreditoRepositorio;

@Named
public class ContaCreditoServico {
    
    private ContaCreditoRepositorio repositorio;

    @Inject 
    public ContaCreditoServico(ContaCreditoRepositorio repositorio) {
        this.repositorio = repositorio;
    }
    
    public void save(ContaCredito objeto) {
        // realizar verificacoes de negocio        
        
        // delega a persistencia para o repositorio
        repositorio.save(objeto);
    }
    
    public void delete(ContaCredito objeto) {
        // realizar verificacoes de negocio
                
        // delega a persistencia para o repositorio
        repositorio.delete(objeto);
    }
    
    public Iterator<ContaCredito> iterator() {
        return repositorio.iterator();
    }
    
}
