package br.edu.ifrn.conta.servico;

import br.edu.ifrn.conta.dominio.Categoria;
import java.util.Iterator;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.ifrn.conta.persistencia.CategoriaRepositorio;

@Named
public class CategoriaServico {
    
    private CategoriaRepositorio repositorio;

    @Inject 
    public CategoriaServico(CategoriaRepositorio repositorio) {
        this.repositorio = repositorio;
    }
    
    public void save(Categoria objeto) {
        // realizar verificacoes de negocio
                
        // delega a persistencia para o repositorio
        repositorio.save(objeto);
    }
    
    public void delete(Categoria objeto) {
        // realizar verificacoes de negocio
                
        // delega a persistencia para o repositorio
        repositorio.delete(objeto);
    }
    
    public Iterator<Categoria> iterator() {
        return repositorio.iterator();
    }
    
}
