package br.edu.ifrn.conta.servico;

import java.io.Serializable;
import javax.inject.Inject;
import org.springframework.data.repository.CrudRepository;

public class AbstratoServico <T extends Object, ID extends Serializable> {
    
    private CrudRepository<T, ID> repository;

    @Inject
    public void setRepositorio(CrudRepository<T, ID> repository) {
        this.repository = repository;
    }
    
    public void save(T objeto) {
        // realizar verificacoes de negocio
                
        // delega a persistencia para o repositorio
        repository.save(objeto);
    }
    
    public void delete(T objeto) {
        // realizar verificacoes de negocio
                
        // delega a persistencia para o repositorio
        repository.delete(objeto);
    }
    
    public Iterable<T> findAll() {
        return repository.findAll();
    }
    
    public void deleteAll() {
        // realizar verificacoes de negocio
                
        // delega a persistencia para o repositorio
        repository.deleteAll();
    }
    
}
