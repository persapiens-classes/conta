package br.edu.ifrn.conta.controller;

import br.edu.ifrn.conta.service.CrudService;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Service of Lancamento.
 */
public class CrudController <T extends Object, ID extends Serializable> {

    @Autowired
    private CrudService<T, ID> crudService;
    
    @PostMapping
    public T save(@RequestBody T entity) {
        return crudService.save(entity);
    }
    
    @GetMapping
    public Iterable<T> findAll() {
        return crudService.findAll();
    }
}
