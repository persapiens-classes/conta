package br.edu.ifrn.conta.service;

import br.edu.ifrn.conta.domain.ContaPatrimonio;
import br.edu.ifrn.conta.persistence.ContaPatrimonioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service of ContaPatrimonio.
 */
@Service
public class ContaPatrimonioService extends ContaService<ContaPatrimonio, Long> {
    
    @Autowired
    private ContaPatrimonioRepository donoRepository;
    
    public ContaPatrimonio findByDescricao(String descricao) {
        return donoRepository.findByDescricao(descricao);
    }
}
