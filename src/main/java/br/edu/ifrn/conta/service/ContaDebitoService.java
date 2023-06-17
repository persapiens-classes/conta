package br.edu.ifrn.conta.service;

import br.edu.ifrn.conta.domain.ContaDebito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service of ContaDebito.
 */
@Service
public class ContaDebitoService extends ContaService<ContaDebito, Long> {
    @Autowired
    private CategoriaService categoriaService;
    
    public ContaDebito despesaTransferencia() {
        ContaDebito result = findByDescricao(ContaDebito.DESPESA_TRANSFERENCIA);
        if (result == null) {
            result = ContaDebito.builder()
                    .descricao(ContaDebito.DESPESA_TRANSFERENCIA)
                    .categoria(categoriaService.despesaTransferencia())
                    .build();
            result = save(result);
        }
        return result;
    }
}
