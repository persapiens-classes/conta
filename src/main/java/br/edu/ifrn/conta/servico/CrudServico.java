package br.edu.ifrn.conta.servico;

import java.io.Serializable;
import javax.inject.Inject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacao padrao de servico crud delegando a implementacao para o repositorio
 */
@Transactional(readOnly = true)
public class CrudServico <T extends Object, ID extends Serializable> {
    
    private CrudRepository<T, ID> repository;

    @Inject
    public void setRepositorio(CrudRepository<T, ID> repository) {
        this.repository = repository;
    }
    
    @Transactional
    public <S extends T> Iterable<S> save(Iterable<S> objetos) {
        return repository.save(objetos);
    }
    
    @Transactional
    public <S extends T> S save(S objeto) {
        return repository.save(objeto);
    }
    
    @Transactional
    public void delete(Iterable<? extends T> objetos) {
        repository.delete(objetos);
    }
    
    @Transactional
    public void delete(ID id) {
        repository.delete(id);
    }
    
    @Transactional
    public void delete(T objeto) {
        repository.delete(objeto);
    }
    
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }
    
    public T findOne(ID id) {
        return repository.findOne(id);
    }
    
    public Iterable<T> findAll() {
        return repository.findAll();
    }
    
    public Iterable<T> findAll(Iterable<ID> ids) {
        return repository.findAll(ids);
    }
    
    public long count() {
        return repository.count();
    }
    
    public boolean exists(ID id) {
        return repository.exists(id);
    }
    
}
