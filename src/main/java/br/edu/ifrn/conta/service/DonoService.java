package br.edu.ifrn.conta.service;

import br.edu.ifrn.conta.domain.Dono;
import br.edu.ifrn.conta.persistence.DonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service of Dono.
 */
@Service
public class DonoService extends CrudService<Dono, Long> {
    
    @Autowired
    private DonoRepository donoRepository;
    
    public Dono findByDescricao(String descricao) {
        return donoRepository.findByDescricao(descricao);
    }
}
