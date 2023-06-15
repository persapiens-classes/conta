package br.edu.ifrn.conta.service;

import br.edu.ifrn.conta.domain.Categoria;
import br.edu.ifrn.conta.persistence.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service of Categoria.
 */
@Service
public class CategoriaService extends CrudService<Categoria, Long> {
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public Categoria findByDescricao(String descricao) {
        return categoriaRepository.findByDescricao(descricao);
    }
}
